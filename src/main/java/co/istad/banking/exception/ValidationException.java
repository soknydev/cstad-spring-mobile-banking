package co.istad.banking.exception;

import co.istad.banking.base.BasedError;
import co.istad.banking.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedErrorResponse handleValidationErrors(MethodArgumentNotValidException ex){
        BasedError<List<?>> basedError = new BasedError<>();
        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(
                        fieldError -> {
                            Map<String, Object> error = new HashMap<>();
                            error.put("filed",fieldError.getField());
                            error.put("reason",fieldError.getDefaultMessage());
                            errors.add(error);
                        }
                );
        basedError.setCode(HttpStatus.BAD_GATEWAY.getReasonPhrase());
        basedError.setDescription(errors);
        return new BasedErrorResponse(basedError);
    }
}
