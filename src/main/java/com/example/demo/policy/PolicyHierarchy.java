package com.example.demo.policy;

import com.example.demo.policy.cell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class PolicyHierarchy<K extends Cell> extends PolicyColumn<K> {
    public PolicyHierarchy(final Entity entity, final Integer indexInResultSet) {
        super(entity, new ArrayList<>());
    }

    @Override
    public List<K> calculateValues() {
        return new ArrayList<>();
    }
}
