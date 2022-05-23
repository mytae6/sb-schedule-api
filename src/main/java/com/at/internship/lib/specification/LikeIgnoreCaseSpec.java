
package com.at.internship.lib.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LikeIgnoreCaseSpec<T> implements Specification<T> {
    private final String property;
    private final String value;
    public LikeIgnoreCaseSpec(String property, String value) {
        if(property == null || property.isBlank()) throw new IllegalArgumentException("Null property name provided to LikeIgnoreCaseSpec");
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return value == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(criteriaBuilder.upper(root.get(property)), value.toUpperCase());
    }
}