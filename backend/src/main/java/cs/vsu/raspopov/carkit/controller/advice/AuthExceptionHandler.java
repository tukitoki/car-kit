package cs.vsu.raspopov.carkit.controller.advice;

import cs.vsu.raspopov.carkit.exception.AuthException;
import cs.vsu.raspopov.carkit.exception.message.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessage> handleAuthException(AuthException ex) {
        AuthException.CODE code = ex.getCode();
        HttpStatus status = switch (code) {
            case JWT_VALIDATION_ERROR -> BAD_REQUEST;
        };

        String codeStr = code.toString();

        log.error(codeStr, ex);

        return ResponseEntity
                .status(status)
                .body(new ErrorMessage(codeStr, ex.getMessage()));
    }
}
