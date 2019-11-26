package com.example.demo.policy;

import com.example.demo.policy.behaviour.Behaviour;
import com.example.demo.policy.cell.Cell;
import com.example.demo.policy.core.ColumnType;

import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public abstract class PolicyColumn<K extends Cell> implements Column<K> {
    private Integer levelColumn;

    private ColumnType columnType;

    private final Entity entity;

    private final List<Behaviour<K>> behaviours;

    public PolicyColumn(final Entity entity, final List<Behaviour<K>> behaviours) {
        this.entity = entity;
        this.behaviours = behaviours;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}
