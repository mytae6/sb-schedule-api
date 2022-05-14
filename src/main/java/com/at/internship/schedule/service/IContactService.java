package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> findAll();
    Contact create(Contact contact);
}
