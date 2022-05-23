package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.dto.AppointmentFilterDto;
import com.at.internship.schedule.dto.AppointmentFilterDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAppointmentService {
    Page<Appointment> findAll(AppointmentFilterDto filters, Pageable pageable);
    Appointment create(Appointment appointment);
    Appointment update(Appointment appointment);
    Optional<Appointment> findById(Integer id);
    void delete(Integer id);
}
