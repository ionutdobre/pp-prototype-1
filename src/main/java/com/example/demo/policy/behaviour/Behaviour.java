package com.example.demo.policy.behaviour;

import com.example.demo.policy.cell.Cell;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface Behaviour<K extends Cell> {
    K apply(final K cell);
}
