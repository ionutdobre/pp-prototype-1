package com.example.demo.policy;

import com.example.demo.policy.cell.Cell;

import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface Column<K extends Cell> {
    List<K> calculateValues();

    Entity getEntity();
}
