package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.RequestDto;
import cs.vsu.raspopov.carkit.entity.Request;
import cs.vsu.raspopov.carkit.repository.RequestRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepo requestRepo;
    private final ModelMapper mapper = new ModelMapper();

    public RequestDto applyRequest(RequestDto requestDto) {
        Request request = mapper.map(requestDto, Request.class);
        request = requestRepo.save(request);
        return mapper.map(request, RequestDto.class);
    }
}
