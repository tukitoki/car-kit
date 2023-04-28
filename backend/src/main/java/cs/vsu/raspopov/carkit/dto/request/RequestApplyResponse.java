package cs.vsu.raspopov.carkit.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class RequestApplyResponse {

    private RequestTime requestTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String phoneNumber;
}
