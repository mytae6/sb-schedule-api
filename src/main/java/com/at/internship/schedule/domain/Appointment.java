package com.at.internship.schedule.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@NoArgsConstructor
public class Appointment implements Serializable {
    private static final String SEQUENCE_NAME = "APPOINTMENT_SEQUENCE";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    private Integer id;
    @Column(name = "contact_id")
    private Integer contactId;
    @Column(name = "local_date_time")
    private LocalDateTime time;
    private String subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Contact contact;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("%s. %s -- %s : %s", id, time, contact, subject);
    }

}
