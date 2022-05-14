package com.at.internship.schedule.response;

import lombok.Data;

@Data
public class GenericResponse<T> {
    private String code;
    private String message;
    private T content;
}