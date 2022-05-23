package com.at.internship.schedule.dto;

import lombok.Data;

@Data
public class ContactPhoneDto {
    private Integer id;
    private Integer contactId;
    private String phoneNumber;
    private String label;
}
