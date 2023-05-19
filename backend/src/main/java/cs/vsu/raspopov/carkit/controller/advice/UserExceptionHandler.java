package cs.vsu.raspopov.carkit.controller.advice;

import cs.vsu.raspopov.carkit.exception.UserException;
import cs.vsu.raspopov.carkit.exception.message.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorMessage> handleUserException(UserException ex) {
        UserException.CODE code = ex.getCode();
        HttpStatus status = switch (code) {
            case USERNAME_NOT_FOUND, ID_NOT_FOUND -> NOT_FOUND;
            case USERNAME_ALREADY_PRESENT, EMAIL_ALREADY_PRESENT -> BAD_REQUEST;
        };

        String codeStr = code.toString();

        log.error(codeStr, ex);

        return ResponseEntity
                .status(status)
                .body(new ErrorMessage(codeStr, ex.getMessage()));
    }
}
