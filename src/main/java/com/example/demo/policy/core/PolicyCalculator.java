package com.example.demo.policy.core;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface PolicyCalculator {
    GeneratedPolicy calculate(PolicySpecification policySpecification) throws Exception;
}
