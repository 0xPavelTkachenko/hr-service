package www.service.hrservice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import www.service.hrservice.interceptor.exception.ForbiddenException;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Result;
import www.service.hrservice.service.exception.ValidationException;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Result> handleValidationException(ValidationException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result(false, e.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Result> handleObjectNotFoundException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String message = messageSource.getMessage("error.object.notfound", null, Locale.getDefault());
        return new ResponseEntity<>(new Result(false, message), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Result> handleForbiddenException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String message = messageSource.getMessage("error.forbidden", null, Locale.getDefault());
        return new ResponseEntity<>(new Result(false, message), headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        e.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String message = messageSource.getMessage("error.undefined", null, Locale.getDefault());
        return new ResponseEntity<>(new Result(false, message), headers, HttpStatus.BAD_REQUEST);
    }

}
