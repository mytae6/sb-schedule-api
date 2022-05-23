package com.at.internship.schedule.utils;

import com.at.internship.lib.specification.EqualSpec;
import com.at.internship.lib.specification.GreaterSpec;
import com.at.internship.lib.specification.LowerSpec;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.ZoneId;


public class ContactSpecificationsHelper {
    public static Specification<Contact> getContactSpecs(ContactFilterDto filters){
        String nameLike = filters.getNameLike() == null ? null :
                String.format("%%%s%%",filters.getNameLike());
        LocalDate birthDay = filters.getBirthDay() == null
                ? null
                : LocalDate
                .ofInstant(filters.getBirthDay().toInstant(), ZoneId.systemDefault());

        LocalDate birthDayGte = filters.getBirthDayGte() == null
                ? null
                : LocalDate
                .ofInstant(filters.getBirthDayGte().toInstant(), ZoneId.systemDefault());
        LocalDate birthDayLte = filters.getBirthDayLte() == null
                ? null
                : LocalDate
                .ofInstant(filters.getBirthDayLte().toInstant(), ZoneId.systemDefault());

        return Specification
                .where(new EqualSpec<Contact>("id", filters.getId()))
                .and(new EqualSpec<>("firstName",filters.getFirstName()))
                .and(new EqualSpec<>("lastName",filters.getLastName()))
                .and(new NameLikeSpec<>(nameLike))
                .and(new EqualSpec<>("emailAddress",filters.getEmailAddress()))
                .and(new EqualSpec<>("birthDay",birthDay))
                .and(new GreaterSpec<>("birthDay",birthDayGte))
                .and(new LowerSpec<>("birthDay",birthDayLte));
    }

    private static class NameLikeSpec<Contact> implements Specification<Contact>{
        private final String name;
        private NameLikeSpec(String name){
            this.name=name;
        }

        @Override
        public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return name == null?criteriaBuilder.conjunction()
                    : criteriaBuilder.like(
                    criteriaBuilder.upper(criteriaBuilder.concat(
                            criteriaBuilder.concat(root.get("firstName")," "),
                            root.get("lastName"))), name.toUpperCase());
        }
    }
}