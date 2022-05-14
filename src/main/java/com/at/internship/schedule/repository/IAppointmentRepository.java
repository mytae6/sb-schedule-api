package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAll();
}
