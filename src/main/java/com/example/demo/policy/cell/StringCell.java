package com.example.demo.policy.cell;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class StringCell extends Cell<String> {
    protected StringCell(final Long entityId) {
        super(entityId);
    }

    @Override
    public String getDisplayedValue() {
        return null;
    }
}
