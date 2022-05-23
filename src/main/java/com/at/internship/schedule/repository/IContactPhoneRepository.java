package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.domain.ContactPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactPhoneRepository extends JpaRepository<ContactPhone, Integer> {
    List<ContactPhone> findAllByContact(Contact contact);
}
