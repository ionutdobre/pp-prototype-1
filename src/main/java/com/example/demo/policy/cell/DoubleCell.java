package com.example.demo.policy.cell;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class DoubleCell extends Cell<Double> {
    protected DoubleCell(final Long entityId) {
        super(entityId);
    }

    @Override
    public String getDisplayedValue() {
        return null;
    }
}
