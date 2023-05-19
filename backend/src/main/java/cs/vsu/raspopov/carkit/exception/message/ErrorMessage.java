package cs.vsu.raspopov.carkit.exception.message;

import lombok.Builder;

@Builder
public record ErrorMessage(String code, String message) {
}
