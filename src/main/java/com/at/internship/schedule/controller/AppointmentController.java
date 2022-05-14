package com.at.internship.schedule.controller;

import com.at.internship.schedule.converter.AppointmentConverter;
import com.at.internship.schedule.dto.AppointmentDto;
import com.at.internship.schedule.service.IAppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
@SuppressWarnings("unused")
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

    @GetMapping("/all")
    public List<AppointmentDto> findAll() {
        return appointmentService.findAll()
                .stream()
                .map(appointmentConverter::toAppointmentDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    public AppointmentDto create(@RequestBody AppointmentDto appointment){
        return appointmentConverter.toAppointmentDto(appointmentService.create(appointmentConverter.toAppointment(appointment)));
    }
}
