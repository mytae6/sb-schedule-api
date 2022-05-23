package com.at.internship.schedule.dto;
import javax.validation.constraints.*;

import lombok.Data;

@Data
public class ContactUpdateDto {
    @NotNull(message = "Id is required")
    private Integer id;

    @NotNull(message = "First name is required")
    @Size(min = 2, max = 255, message = "First name should be at least 2 characters length and 255 maximum")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 255, message = "Last name should be at least 2 characters length and 255 maximum")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    private String emailAddress;

    @NotNull(message = "BirthDay is required")
    private String birthDay;
}
