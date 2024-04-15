package people.spheres.demo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<String> handleException(RuntimeException ex) {
        return BadRequestResponseDTO.create(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
