package com.at.internship.schedule.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AppointmentAssis implements Serializable {
    @EmbeddedId
    private AppointmentAssisFK appointmentAssisFK;
    @Column(name = "type", length = 30, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "appointment_id", insertable = false, updatable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "contact_id", insertable = false, updatable = false)
    private Contact contact;


}
