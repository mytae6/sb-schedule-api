package com.at.internship.schedule.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GenericResponse<T> {
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private T content;
}
