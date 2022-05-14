package com.at.internship.schedule.exception;

import com.at.internship.schedule.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {
    private static final String STR_NESTED_EXCEPTION = "nested exception is";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = e.getMessage();
        if(errorMessage != null && errorMessage.contains(STR_NESTED_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_NESTED_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setCode("INVALID_INPUT");
        response.setMessage("Bad Request");
        response.setErrorMessages(Collections.singletonList(errorMessage));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            if(!(err instanceof FieldError)) return;
            FieldError fieldError = (FieldError) err;
            errorMessages.add(fieldError.getDefaultMessage());
        });
        ErrorResponse response = new ErrorResponse();
        response.setCode("INVALID_INPUT");
        response.setMessage("Bad Request");
        response.setErrorMessages(errorMessages);
        return response;
    }

}
