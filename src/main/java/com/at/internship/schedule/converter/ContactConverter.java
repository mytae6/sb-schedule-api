package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDetailDto;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    private final DateUtils dateUtils;

    public ContactConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactDetailDto toContactDetailDto(Contact contact) {
        ContactDetailDto contactDetailDto = new ContactDetailDto();
        contactDetailDto.setId(contact.getId());
        contactDetailDto.setFirstName(contact.getFirstName());
        contactDetailDto.setLastName(contact.getLastName());
        contactDetailDto.setEmailAddress(contact.getEmailAddress());
        contactDetailDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        return contactDetailDto;
    }

    public Contact updateContactDtoToContact(ContactUpdateDto contactUpdateDto) {
        Contact contact = new Contact();
        contact.setId(contactUpdateDto.getId());
        contact.setFirstName(contactUpdateDto.getFirstName());
        contact.setLastName(contactUpdateDto.getLastName());
        contact.setEmailAddress(contactUpdateDto.getEmailAddress());
        contact.setBirthDay(dateUtils.parseDefaultDate(contactUpdateDto.getBirthDay()));
        return contact;
    }


    public Contact contactCreateDtoToContact(ContactCreateDto contactCreateDtoDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactCreateDtoDto.getFirstName());
        contact.setLastName(contactCreateDtoDto.getLastName());
        contact.setEmailAddress(contactCreateDtoDto.getEmailAddress());
        contact.setBirthDay(dateUtils.parseDefaultDate(contactCreateDtoDto.getBirthDay()));
        return contact;
    }


}