package com.at.internship.schedule.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String emailAddress;
    @Deprecated
    private String phoneNumber;
    private LocalDate birthDay;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;
    @Column(insertable = false)
    private LocalDateTime lastUpdateDate;
    @Transient
    private List<ContactPhone> phoneNumbers;

    public Contact(Contact source) {
        if(source == null)
            return;
        this.id = source.id;
        this.firstName = source.firstName;
        this.lastName = source.lastName;
        this.emailAddress = source.emailAddress;
        this.phoneNumber = source.phoneNumber;
        this.birthDay = source.birthDay;
    }

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
