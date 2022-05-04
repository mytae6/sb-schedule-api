package com.at.internship.schedule.config;

import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRepositoryConfig {
    public static final String REPOSITORY_CONTACT_DEFAULT = "${com.at.internship.repository.contact.default}";
    public static final String REPOSITORY_APPOINTMENT_DEFAULT = "${com.at.internship.repository.appointment.default}";

    private final ApplicationContext context;

    public DefaultRepositoryConfig(ApplicationContext context) {
        this.context = context;
        // this.context.getBean();
    }

    @Value(REPOSITORY_CONTACT_DEFAULT)
    private String defaultContactRepository;

    @Bean(name = REPOSITORY_CONTACT_DEFAULT)
    public IContactRepository contactRepository() {
        return (IContactRepository) context.getBean(defaultContactRepository);
    }

    @Value(REPOSITORY_APPOINTMENT_DEFAULT)
    private String defaultAppointmentRepository;

    @Bean(name = REPOSITORY_APPOINTMENT_DEFAULT)
    public IAppointmentRepository appointmentRepository() {
        return (IAppointmentRepository) context.getBean(defaultAppointmentRepository);
    }

}
