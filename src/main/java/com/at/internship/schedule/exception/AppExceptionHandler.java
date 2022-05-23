package com.at.internship.schedule.exception;

import com.at.internship.schedule.response.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
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
        response.setTimestamp(LocalDateTime.now());
        response.setCode("VALIDATION_FAILED");
        response.setMessage("There were validations errors");
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
        response.setTimestamp(LocalDateTime.now());
        response.setCode("VALIDATION_FAILED");
        response.setMessage("There were validations errors");
        response.setErrorMessages(errorMessages);
        return response;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmptyResultDataAccessExpection(EmptyResultDataAccessException e){
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("RECORD_NOT_FOUND");
        response.setMessage("Record not found");
        response.setErrorMessages(Collections.singletonList(e.getMessage()));
        return response;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("DB_CONSTRAINT_VIOLATION");
        response.setMessage("Database constraint violation");
        response.setErrorMessages(Collections.singletonList(e.getMessage()));
        return response;
    }

}
