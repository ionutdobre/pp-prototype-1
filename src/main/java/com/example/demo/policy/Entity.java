package com.example.demo.policy;

import com.example.demo.persistence.Item;
import com.example.demo.policy.core.EntityType;
import com.example.demo.policy.core.PolicyEngine;

import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface Entity<T> {
    List<T> fetch(PolicyEngine policyEngine) throws Exception;

    List<T> getElements();

    EntityType getType();

    String getName();
}
