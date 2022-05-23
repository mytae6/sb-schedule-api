package com.at.internship.schedule.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;
    @Column(nullable = false, name = "last_name", length = 50)
    private String lastName;
    @Column(nullable = false, name = "email", length = 100)
    private String emailAddress;
    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Formula("CONCAT(first_name,' ', last_name)")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ContactPhone> contactPhones;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Appointment> appointments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id.equals(contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("%s %s (%s)", firstName, lastName, id);
    }

}
