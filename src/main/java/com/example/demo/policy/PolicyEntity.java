package com.example.demo.policy;

import com.example.demo.policy.core.EntityType;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public abstract class PolicyEntity<T> implements Entity<T> {
    protected static final Logger logger = LoggerFactory.getLogger(PolicyEntity.class);

    protected List<T> elements;

    private final EntityType entityType;

    private final String tableName;

    private final String description;

    protected PolicyEntity(final EntityType entityType, final String tableName, final String description) {
        this.entityType = entityType;
        this.tableName = tableName;
        this.description = description;
    }

    @Override
    public EntityType getType() {
        return entityType;
    }

    @Override
    public String getName() {
        return tableName;
    }

    @Override
    public List<T> getElements() {
        if (CollectionUtils.isEmpty(elements)) {
            logger.error("We are trying to use elements for + " + tableName + " before fetching them!");
        }
        return elements;
    }
}
