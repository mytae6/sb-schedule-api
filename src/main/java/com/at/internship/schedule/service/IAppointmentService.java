package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Appointment;

import java.util.List;

public interface IAppointmentService {
    List<Appointment> findAll();
    Appointment create(Appointment appointment);
}
