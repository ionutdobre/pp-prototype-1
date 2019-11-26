package com.example.demo.policy.core;

import com.example.demo.policy.Entity;
import com.example.demo.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class PolicyEngine {
    private static final Logger logger = LoggerFactory.getLogger(PolicyEngine.class);

    private final PolicySpecification policySpecification;

    private GeneratedPolicy generatedPolicy;

    private ItemService itemService;

    public PolicyEngine(final PolicySpecification policySpecification) {
        this.policySpecification = policySpecification;
    }

    // TODO - this should be @Autowired or we need to find a better way to inject necessary services...
    public PolicyEngine setItemService(final ItemService itemService) {
        this.itemService = itemService;
        return this;
    }

    public GeneratedPolicy runPolicy() {
        this.generatedPolicy = new GeneratedPolicy();

        applyFilters();
        fetchData();
        calculatePolicy();
        output();

        return this.generatedPolicy;
    }

    private void applyFilters() {
        // TODO - implement this
        policySpecification.getFilter(this);
    }

    private void fetchData() {
        policySpecification.getColumns().parallelStream().forEach(column -> {
            final Entity entity = column.getEntity();
            try {
                entity.fetch(this);
            } catch (Exception e) {
                logger.error("Error fetching Entity: " + entity.getName());
                generatedPolicy.getPolicyWarnings().add(e.getMessage());
            }
        });
    }

    private void calculatePolicy() {

    }

    private void output() {

    }

    public<T> List<T> fetchEntity(Entity<T> entityToFetch) {
        final EntityType entityType = entityToFetch.getType();

        switch (entityType) {
            case ITEM:
                return (List<T>) itemService.findAll();

            default:
                return new ArrayList<>();
        }
    }
}
