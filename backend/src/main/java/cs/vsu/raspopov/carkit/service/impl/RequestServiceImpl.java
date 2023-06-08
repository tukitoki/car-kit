package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.detail.DetailResponse;
import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.dto.page.SortDirection;
import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestDto;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import cs.vsu.raspopov.carkit.entity.*;
import cs.vsu.raspopov.carkit.mapper.CarMapper;
import cs.vsu.raspopov.carkit.repository.RequestRepo;
import cs.vsu.raspopov.carkit.repository.ScheduleRepo;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.DetailService;
import cs.vsu.raspopov.carkit.service.RequestService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static cs.vsu.raspopov.carkit.dto.page.SortDirection.ASC;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final MaintenanceWorkServiceImpl maintenanceWorkService;
    private final CarService carService;
    private final DetailService detailService;
    private final ScheduleRepo scheduleRepo;
    private final RequestRepo requestRepo;
    private final CarMapper carMapper;
    private final EntityManager entityManager;

    @Override
    public PageModel<RequestDto> getAllCars(int pageNumber, int pageSize, SortDirection order) {
        var page = filerPage(pageNumber, pageSize, order);

        List<RequestDto> requests = page.getContent()
                .stream()
                .map(request -> {
                    List<DetailResponse> detailResponses = request.getDetails()
                            .stream()
                            .map(detail -> {
                                var detailResponse = DetailResponse.builder()
                                        .id(detail.getId())
                                        .name(detail.getName())
                                        .build();
                                return detailResponse;
                            })
                            .toList();

                    var requestDto = RequestDto.builder()
                            .id(request.getId())
                            .startTime(request.getStartTime().toString())
                            .endTime(request.getEndTime().toString())
                            .phoneNumber(request.getPhoneNumber())
                            .carDto(carMapper.toDto(request.getCar()))
                            .details(detailResponses)
                            .build();
                    return requestDto;
                })
                .toList();

        return PageModel.of(requests, pageNumber, page.getTotalElements(),
                pageSize, page.getTotalPages());
    }

    @Override
    public void applyRequest(RequestApplyResponse requestApply) {
        List<MaintenanceWork> works = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        requestApply.getMap().forEach((id, count) -> {
            var detail = detailService.getDetailById(id);
            for (int i = 0; i < count; i++) {
                details.add(detail);
            }
            works.add(maintenanceWorkService.getMaintenanceWork(requestApply.getRequestTime().getCarId(),
                    detail.getDetailType().getId()));
        });
        var date = LocalDate.parse(requestApply.getDate());
        var startTime = LocalTime.parse(requestApply.getStartTime());
        var endTime = getSumTime(requestApply.getMap().keySet().stream().toList(),
                requestApply.getRequestTime().getCarId());
        endTime = endTime.plusHours(startTime.getHour())
                .plusMinutes(startTime.getMinute());

        var car = carService.getCar(requestApply.getRequestTime().getCarId());

        requestRepo.save(Request.builder()
                .startTime(LocalDateTime.of(date, startTime))
                .endTime(LocalDateTime.of(date, endTime))
                .phoneNumber(requestApply.getPhoneNumber())
                .details(details)
                .maintenanceWork(works)
                .car(car)
                .build());
    }

    @Override
    public RequestTimeResponse infoRequest(RequestTime requestTime) {
        var sum = getSumTime(requestTime.getDetails().keySet().stream().toList(),
                requestTime.getCarId());

        var mapDate = getAllInDate(sum);
        return RequestTimeResponse.builder()
                .availableTime(mapDate)
                .build();
    }

    private Map<String, List<String>> getAllInDate(LocalTime timeToChange) {
        Map<String, List<String>> times = new HashMap<>();
        var requests = requestRepo.findAll();

        scheduleRepo.findAll().forEach(schedule -> {
            var availableTime = new ArrayList<LocalTime>();
            var todayRequests = new HashSet<Request>();
            var endTimeWork = LocalDateTime.of(schedule.getDate(), schedule.getEndWorkTime());

            requests.forEach(request -> {
                var date = request.getStartTime().toLocalDate();
                if (date.isEqual(schedule.getDate())) {
                    todayRequests.add(request);
                }
            });

            for (var time = schedule.getStartWorkTime();
                 time.isBefore(schedule.getEndWorkTime());
                 time = time.plusMinutes(30)) {
                var fullTimeToChange = time.plusHours(timeToChange.getHour())
                        .plusMinutes(timeToChange.getMinute());
                LocalDateTime startTime = LocalDateTime.of(schedule.getDate(), time);
                if (startTime.isBefore(LocalDateTime.now())) {
                    continue;
                }
                LocalDateTime endTime = LocalDateTime.of(schedule.getDate(), fullTimeToChange);
                if (isRightTime(todayRequests, startTime, endTime) && endTime.isBefore(endTimeWork)) {
                    availableTime.add(time);
                }
            }

            var stringTime = availableTime
                    .stream()
                    .map(LocalTime::toString)
                    .toList();
            times.put(schedule.getDate().toString(), stringTime);
        });
        return times;
    }

    private LocalTime getSumTime(List<Long> detailIds, Long carId) {
        var sum = LocalTime.of(0, 0);

        for (var detailId : detailIds) {
            var detail = detailService.getDetailById(detailId);
            var maintenanceTime = maintenanceWorkService
                    .calcTimeByCarId(carId, detail.getDetailType().getId());
            sum = sum.plusHours(maintenanceTime.getHour())
                    .plusMinutes(maintenanceTime.getMinute());
        }
        return sum;
    }

    private boolean isRightTime(HashSet<Request> requests,
                                LocalDateTime startTime, LocalDateTime endTime) {
        for (var request : requests) {
            var startTimeReq = request.getStartTime();
            var endTimeReq = request.getEndTime();

            if ((endTime.isAfter(startTimeReq) && endTime.isBefore(endTimeReq.plusMinutes(15)))
                    || (startTime.isAfter(startTimeReq) && startTime.isBefore(endTimeReq.plusMinutes(15)))) {
                return false;
            }
        }
        return true;
    }

    private Page<Request> filerPage(int pageNumber, int pageSize, SortDirection order) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Request> query = cb.createQuery(Request.class);

        Root<Request> root = query.from(Request.class);

        query.select(root).distinct(true);

        Path<Object> orderByName = root.get("startTime");
        Order dateOrder = order.equals(ASC)
                ? cb.asc(orderByName)
                : cb.desc(orderByName);

        List<Order> orderList = List.of(dateOrder, cb.asc(root.get("id")));
        query.orderBy(orderList);

        TypedQuery<Request> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Request> requests = typedQuery.getResultList();
        long count = countFilteredRequest();

        return new PageImpl<>(requests, pageable, count);
    }


    private long countFilteredRequest() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<Request> root = query.from(Request.class);

        query.select(cb.countDistinct(root));

        TypedQuery<Long> typedQuery = entityManager.createQuery(query);

        return typedQuery.getSingleResult();
    }
}
