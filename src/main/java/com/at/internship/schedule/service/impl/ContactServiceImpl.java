package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFilterDto;
import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.repository.IContactPhoneRepository;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;
import com.at.internship.schedule.utils.ContactSpecificationsHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;


@Service
public class ContactServiceImpl implements IContactService{
    private final IContactRepository contactRepository;
    private final IContactPhoneRepository contactPhoneRepository;
    private final IAppointmentRepository appointmentRepository;

    public ContactServiceImpl(IContactRepository contactRepository, IAppointmentRepository appointmentRepository, IContactPhoneRepository contactPhoneRepository) {
        this.contactRepository = contactRepository;
        this.appointmentRepository = appointmentRepository;
        this.contactPhoneRepository = contactPhoneRepository;
    }

    @Override
    public Page<Contact> findAll(ContactFilterDto filters, Pageable pageable) {
        return contactRepository.findAll(ContactSpecificationsHelper.getContactSpecs(filters),pageable);
    }

    @Override
    public Contact findById(Integer id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> {throw new EntityNotFoundException(String.format("Requested Contact with ID %s was not found", id));});
    }

    @Override
    public Contact save(Contact contact) {
        if(contact.getId() != null) this.findById(contact.getId());

        Contact contactFound = contactRepository.findByEmailAddress(contact.getEmailAddress());
        if(contactFound !=null && !Objects.equals(contact.getId(), contactFound.getId()))
            throw new DataIntegrityViolationException(String.format("The email %s is already taken by another contact", contact.getEmailAddress()));

        Contact contactSaved = contactRepository.save(contact);
        contactSaved.setContactPhones(contactPhoneRepository.findAllByContact(contactSaved));
        return contactSaved;
    }

    @Override
    public Contact delete(Integer id, boolean force) {
        Contact contact = this.findById(id);
        if(!contact.getAppointments().isEmpty()){
            if(force) appointmentRepository.deleteAll(contact.getAppointments());
            else throw new DataIntegrityViolationException(String.format("Contact #%s cannot be deleted because there are appointments assigned to them", id));
        }
        if(!contact.getContactPhones().isEmpty()){
            if(force) contactPhoneRepository.deleteAll(contact.getContactPhones());
            else throw new DataIntegrityViolationException(String.format("Contact #%s cannot be deleted because there are contact phones assigned to them", id));
        }
        contactRepository.delete(contact);
        return contact;
    }
}
