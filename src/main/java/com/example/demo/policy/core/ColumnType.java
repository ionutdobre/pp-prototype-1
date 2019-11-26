package com.example.demo.policy.core;

/**
 * @author idobre
 * @since 24/11/2019
 */
public enum ColumnType implements EnumType {
    IMMUTABLE("IMMUTABLE"),

    COMPUTED("COMPUTED");

    private final String value;

    ColumnType(final String value) {
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