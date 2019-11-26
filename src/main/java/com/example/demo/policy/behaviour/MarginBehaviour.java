package com.example.demo.policy.behaviour;

import com.example.demo.policy.cell.DoubleCell;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class MarginBehaviour implements Behaviour<DoubleCell> {
    private Double cost;

    private Double margin;

    public MarginBehaviour(final Double margin) {
        this.margin = margin;
    }

    @Override
    public DoubleCell apply(final DoubleCell cell) {
        // final Double price = cell.getValue();
        final Double newPrice = (100 * cost) / (100 - margin);

        return (DoubleCell) cell.setValue(newPrice);
    }

    public MarginBehaviour setMargin(final Double margin) {
        this.margin = margin;
        return this;
    }
}
