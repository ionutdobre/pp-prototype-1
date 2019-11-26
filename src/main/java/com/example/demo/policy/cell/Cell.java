package com.example.demo.policy.cell;

/**
 * @author idobre
 * @since 24/11/2019
 *
 * Unfortunately I don't think we can make this Immutable since some {@link Cell}s need to be updated.
 */
public abstract class Cell<V> {
    private V value;

    public final Long entityId;

    protected Cell(final Long entityId) {
        this.entityId = entityId;
    }

    public V getValue() {
        return value;
    }

    public Cell<V> setValue(final V value) {
        this.value = value;
        return this;
    }

    public abstract String getDisplayedValue();
}
