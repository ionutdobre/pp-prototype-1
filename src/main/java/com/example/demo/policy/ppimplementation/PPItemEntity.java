package com.example.demo.policy.ppimplementation;

import com.example.demo.persistence.Item;
import com.example.demo.policy.PolicyEntity;
import com.example.demo.policy.core.EntityType;
import com.example.demo.policy.core.PolicyEngine;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class PPItemEntity extends PolicyEntity<Item> {
    public PPItemEntity(final EntityType entityType, final String tableName, final String description) {
        super(entityType, tableName, description);
    }

    // TODO - this can be moved up in
    @Override
    public synchronized List<Item> fetch(final PolicyEngine policyEngine) throws Exception {
        if (CollectionUtils.isEmpty(elements)) {
            elements = policyEngine.fetchEntity(this);
        }

        return elements;
    }
}
