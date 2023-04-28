package cs.vsu.raspopov.carkit.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class RequestApplyResponse {

    private RequestTime requestTime;
    private String date;
    private String startTime;
    private String phoneNumber;
}
