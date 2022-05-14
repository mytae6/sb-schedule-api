package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.AppointmentDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {
    private static final String ERR_MSG_APPOINTMENT_REQUIRED = "appointment is required";

    private final DateUtils dateUtils;

    public AppointmentConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public AppointmentDto toAppointmentDto(Appointment appointment) {
        if(appointment == null) throw new IllegalArgumentException(ERR_MSG_APPOINTMENT_REQUIRED);
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setContactId(appointment.getContactId());
        appointmentDto.setContactName(toStringContactName(appointment.getContact()));
        appointmentDto.setTime(dateUtils.formatDefault(appointment.getTime()));
        appointmentDto.setSubject(appointment.getSubject());
        return appointmentDto;
    }

    public Appointment toAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setContactId(appointmentDto.getContactId());
        appointment.setSubject(appointmentDto.getSubject());
        appointment.setTime(dateUtils.parseDefaultDateTime(appointmentDto.getTime()));
        return appointment;
    }

    private String toStringContactName(Contact contact) {
        if(contact == null) return null;
        return String.format("%s %s",
                contact.getFirstName() == null ? "" : contact.getFirstName(),
                contact.getLastName() == null ? "" : contact.getLastName()).trim();
    }

}
