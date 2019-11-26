package com.example.demo.policy.cell;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class IntegerCell extends Cell<Integer> {
    protected IntegerCell(final Long entityId) {
        super(entityId);
    }

    @Override
    public String getDisplayedValue() {
        return null;
    }
}
