package com.example.demo.policy.core;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface PolicyFilter<D> {
    Specification<D> getSpecification();
}
