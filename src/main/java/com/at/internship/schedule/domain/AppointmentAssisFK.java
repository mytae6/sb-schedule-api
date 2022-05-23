
package com.at.internship.schedule.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class AppointmentAssisFK implements Serializable {
    @Column(name = "Appointment_id", length = 11, nullable = false)
    private Integer appointmentID;
    @Column(name = "contact_id", length = 11, nullable = false)
    private Integer contactId;
}
