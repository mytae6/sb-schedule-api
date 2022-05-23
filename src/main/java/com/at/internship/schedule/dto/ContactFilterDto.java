package com.at.internship.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContactFilterDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nameLike;
    private String emailAddress;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDay;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDayGte;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDayLte;
}