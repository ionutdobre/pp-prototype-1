package com.example.demo.policy.core;

import com.example.demo.policy.Column;
import com.example.demo.policy.PolicyEntity;
import com.example.demo.policy.PolicyHierarchy;
import com.example.demo.policy.cell.Cell;

import java.util.List;
import java.util.Set;

/**
 * @author idobre
 * @since 24/11/2019
 */
public interface PolicySpecification {
    String getPolicyName();

    Set<Column<? extends Cell>> getColumns();

    /**
     * This set should be a strict subset of the one returned at {@link #getColumns()}
     */
    Set<PolicyHierarchy<? extends Cell>> getHierarchies();

    PolicyFilter getFilter(PolicyEngine policyEngine);

    List<SortingInfo> getSorters();

    List<GroupingInfo> getGroupingCriteria();

    default<T> List<T> fetchEntity(PolicyEngine policyEngine, PolicyEntity<T> entity) throws Exception {
        return entity.fetch(policyEngine);
    }
}
