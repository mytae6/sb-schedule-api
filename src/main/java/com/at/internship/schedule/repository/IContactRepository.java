package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {
    Contact findByEmailAddress(String email);
}
