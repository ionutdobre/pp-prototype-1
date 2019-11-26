package com.example.demo.policy.core;

/**
 * @author idobre
 * @since 24/11/2019
 */
public enum EntityType implements EnumType {
    ITEM("ITEM");

    private final String value;

    EntityType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
