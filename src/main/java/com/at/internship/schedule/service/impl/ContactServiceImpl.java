package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;

    public ContactServiceImpl(
            IContactRepository contactRepository
    ) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }
}
