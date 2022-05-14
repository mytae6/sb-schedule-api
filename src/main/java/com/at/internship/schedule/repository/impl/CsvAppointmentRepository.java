package com.at.internship.schedule.repository.impl;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.lib.repository.AbstractRepository;
import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.serialization.csv.AppointmentSerializer;

import java.time.LocalDate;
import java.util.List;

@Deprecated
public class CsvAppointmentRepository extends AbstractRepository<Appointment, Integer> {
    private static int ID_SEQUENCE = 0;
    private static CsvAppointmentRepository SINGLETON;

    public static CsvAppointmentRepository getSingleton() {
        if(SINGLETON == null) SINGLETON = new CsvAppointmentRepository();
        return SINGLETON;
    }

    private CsvContactRepository contactRepository;

    private CsvAppointmentRepository() {
        super(new AppointmentSerializer());
    }

    private CsvContactRepository getContactRepository() {
        if(contactRepository == null)
            contactRepository = CsvContactRepository.getSingleton();
        return contactRepository;
    }

    public List<Appointment> findAllByDate(LocalDate date) {
        return findAll(c -> date.isEqual(c.getTime().toLocalDate()));
    }

    private class InnerAppointment extends Appointment {
        private boolean contactLoaded = false;

        private InnerAppointment(Appointment source) {
            super(source);
        }

        public Contact getContact() {
            if(!contactLoaded) {
                if(super.getContact() == null && getContactId() != null)
                    setContact(getContactRepository().findOne(getContactId()).orElse(null));
                contactLoaded = true;
            }

            return super.getContact();
        }
    }

    @Override
    protected Appointment cloneRow(Appointment source) {
        return new InnerAppointment(source);
    }

    @Override
    protected Integer getId(Appointment source) {
        return source.getId();
    }

    @Override
    protected void assignId(Appointment newRow) {
        newRow.setId(++ID_SEQUENCE);
    }
}
