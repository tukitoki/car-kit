package cs.vsu.raspopov.carkit.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutoRepairShopDto {

    private Integer id;

    private String address;

    private List<ContactDto> contacts;

    private List<ScheduleDto> scheduleDto;

    private List<RequestDto> requests;
}
