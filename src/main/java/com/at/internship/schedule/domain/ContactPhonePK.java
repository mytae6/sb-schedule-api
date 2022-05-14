package com.at.internship.schedule.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ContactPhonePK implements Serializable {
    private Integer contactId;
    private String phoneNumber;
}
