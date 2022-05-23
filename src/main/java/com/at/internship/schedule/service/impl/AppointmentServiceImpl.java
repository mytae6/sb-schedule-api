package com.at.internship.schedule.service.impl;

import com.at.internship.lib.specification.EqualSpec;
import com.at.internship.lib.specification.GreaterSpec;
import com.at.internship.lib.specification.LikeIgnoreCaseSpec;
import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.dto.AppointmentFilterDto;
import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.service.IAppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final IAppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(
            IAppointmentRepository appointmentRepository
    ) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Page<Appointment> findAll(AppointmentFilterDto filters, Pageable pageable) {
        // Concatenating "%" + filters.getSubjectLike() + "%"
        String subjectLike = filters.getSubjectLike() == null
                ? null
                : String.format("%%%s%%", filters.getSubjectLike());
        // If timeGreaterThan is not null, transform into instant
        LocalDateTime timeGraterThan = filters.getTimeGreaterThan() == null
                ? null
                : LocalDateTime
                .ofInstant(filters.getTimeGreaterThan().toInstant(), ZoneId.systemDefault());
        // Concatenating "%" + filters.getContactNameLike() + "%"
        String contactNameLike = filters.getContactNameLike() == null
                ? null
                : String.format("%%%s%%", filters.getContactNameLike());
        // Define specifications
        Specification<Appointment> specs = Specification
                .where(new EqualSpec<Appointment>("contactId", filters.getContactId()))
                .and(new LikeIgnoreCaseSpec<>("subject", subjectLike))
                .and(new GreaterSpec<>("time", timeGraterThan))
                .and(new ContactNameLikeSpec<>(contactNameLike));
        // Execute query
        return appointmentRepository.findAll(specs, pageable);
    }

    @Override
    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        appointmentRepository.deleteById(id);
    }

    public static class ContactNameLikeSpec<Appointment> implements Specification<Appointment> {
        private String contactName;
        private ContactNameLikeSpec(String contactName) {
            this.contactName = contactName;
        }


        @Override
        public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return contactName == null
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.like(criteriaBuilder.upper(root.join("contact").get("firstName")), contactName.toUpperCase());
        }
    }

}
