package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.config.DefaultRepositoryConfig;
import com.at.internship.schedule.config.MockRepositoryConfig;
import com.at.internship.schedule.config.CsvRepositoryConfig;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;

    public ContactServiceImpl(
            @Qualifier(DefaultRepositoryConfig.REPOSITORY_CONTACT_DEFAULT)
            IContactRepository contactRepository
    ) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
