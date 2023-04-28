package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;

public interface RequestService {

    RequestTimeResponse infoRequest(RequestTime requestTime);
}
