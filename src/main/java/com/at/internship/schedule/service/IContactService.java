package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IContactService {
    Page<Contact> findAll(ContactFilterDto filters, Pageable pageable);

    Contact findById(Integer id);

    Contact save(Contact contact);

    Contact delete(Integer id, boolean force);
}
