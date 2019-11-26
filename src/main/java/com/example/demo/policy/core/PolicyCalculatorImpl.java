package com.example.demo.policy.core;

import com.example.demo.services.ItemService;

/**
 * @author idobre
 * @since 24/11/2019
 *
 * TODO - this could be a @Service...
 */
public class PolicyCalculatorImpl implements PolicyCalculator {
    private final PolicySchema policySchema;

    private ItemService itemService;

    public PolicyCalculatorImpl(final PolicySchema policySchema, final ItemService itemService) {
        this.policySchema = policySchema;
        this.itemService = itemService;
    }

    @Override
    public GeneratedPolicy calculate(PolicySpecification policySpecification) throws Exception {
        final PolicyEngine policyEngine = new PolicyEngine(policySpecification)
                .setItemService(itemService);

        return policyEngine.runPolicy();
    }
}
