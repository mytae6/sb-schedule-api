package com.at.internship.schedule.controller;
import com.at.internship.schedule.converter.AppointmentConverter;
import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.dto.*;
import com.at.internship.schedule.response.GenericResponse;
import com.at.internship.schedule.service.IAppointmentService;
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
@RequestMapping("/appointment")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final AppointmentConverter appointmentConverter;

    public AppointmentController(
            IAppointmentService appointmentService,
            AppointmentConverter appointmentConverter
    ) {
        this.appointmentService = appointmentService;
        this.appointmentConverter = appointmentConverter;
    }

    @GetMapping("/get/{id}")
    public GenericResponse<AppointmentDto> getContactById(@PathVariable("id") Integer id){
        GenericResponse<AppointmentDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(appointmentConverter.toAppointmentDto(Objects.requireNonNull(appointmentService.findById(id).orElse(null))));
        return response;
    }

    @GetMapping("/find")
    public GenericResponse<Page<AppointmentDto>> findAll(AppointmentFilterDto filters, Pageable pageable) {
        GenericResponse<Page<AppointmentDto>> response = new GenericResponse<>();
        Page<Appointment> page = appointmentService.findAll(filters, pageable);
        List<AppointmentDto> content = page
                .stream()
                .map(appointmentConverter::toAppointmentDto)
                .collect(Collectors.toList());

        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(new PageImpl<>(content, page.getPageable(), page.getTotalElements()));
        return response;
    }

    @PutMapping("/update")
    public GenericResponse<AppointmentDto> update(@RequestBody @Valid AppointmentDto appointmentDto) {
        GenericResponse<AppointmentDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(appointmentConverter.toAppointmentDto(appointmentService.update(appointmentConverter.toAppointment(appointmentDto))));
        return response;
    }

    @PostMapping("/new")
    public GenericResponse<AppointmentDto> create(@RequestBody @Valid AppointmentDto appointmentDto) {
        GenericResponse<AppointmentDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        response.setContent(appointmentConverter.toAppointmentDto(appointmentService.create(appointmentConverter.toAppointment(appointmentDto))));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> delete(@PathVariable("id") Integer id) {
        GenericResponse<?> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode("OK");
        response.setMessage("SUCCESS!");
        appointmentService.delete(id);
        return response;
    }
}
