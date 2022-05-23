package com.at.internship.schedule.controller;

import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactDetailDto;
import com.at.internship.schedule.dto.ContactFilterDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.response.GenericResponse;
import com.at.internship.schedule.service.IContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
@SuppressWarnings("unused")
public class ContactController {

    private final IContactService contactService;
    private final ContactConverter contactConverter;

    public ContactController(IContactService contactService, ContactConverter contactConverter){
        this.contactConverter = contactConverter;
        this.contactService = contactService;
    }

    @GetMapping("/get/{id}")
    public GenericResponse<ContactDetailDto> getContactById(@PathVariable("id") Integer id){
        GenericResponse<ContactDetailDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(contactConverter.toContactDetailDto(Objects.requireNonNull(contactService.findById(id))));
        return response;
    }

    @GetMapping("/find")
    public GenericResponse<Page<ContactDetailDto>> findAll(ContactFilterDto filters, Pageable pageable){
        GenericResponse<Page<ContactDetailDto>> response = new GenericResponse<>();

        Page<Contact> page = contactService.findAll(filters, pageable);
        List<ContactDetailDto> content = page
                .stream()
                .map(contactConverter::toContactDetailDto)
                .collect(Collectors.toList());

        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(new PageImpl<>(content, page.getPageable(), page.getTotalElements()));


        return response;
    }


    @PutMapping("/update")
    public GenericResponse<ContactDetailDto> update(@RequestBody @Valid ContactUpdateDto contactUpdateDto) {
        GenericResponse<ContactDetailDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(contactConverter.toContactDetailDto(contactService.save(contactConverter.updateContactDtoToContact(contactUpdateDto))));
        return response;
    }

    @PostMapping("/new")
    public GenericResponse<ContactDetailDto> create(@RequestBody @Valid ContactCreateDto contactCreateDto) {
        GenericResponse<ContactDetailDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(contactConverter.toContactDetailDto(contactService.save(contactConverter.contactCreateDtoToContact(contactCreateDto))));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> delete(@PathVariable("id") Integer id, @RequestParam(required = false) boolean force) {
        GenericResponse<?> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        contactService.delete(id, force);
        return response;
    }
}
