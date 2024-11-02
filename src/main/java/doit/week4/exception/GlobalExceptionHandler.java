package doit.week4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DuplicateEmailException.class) //해당 클래스의 자손들만 처리하겠다는 뜻
    @ResponseStatus(HttpStatus.CONFLICT) // response entity에 상태를 저장하는 것 대신 response status를 쓸 수도 있음
    public String handleDuplicateEmailException(DuplicateEmailException e) {
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}
