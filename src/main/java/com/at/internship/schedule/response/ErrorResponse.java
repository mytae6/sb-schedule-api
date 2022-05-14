package com.at.internship.schedule.response;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse extends GenericResponse {
    List<String> errorMessages;
}