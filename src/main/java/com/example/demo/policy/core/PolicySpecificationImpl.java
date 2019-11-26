package com.example.demo.policy.core;

import com.example.demo.policy.Column;
import com.example.demo.policy.PolicyHierarchy;
import com.example.demo.policy.cell.Cell;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class PolicySpecificationImpl implements PolicySpecification {
    private final String policyName;

    private final Set<Column<? extends Cell>> columns = new LinkedHashSet<>();

    private final Set<PolicyHierarchy<? extends Cell>> hierarchies = new LinkedHashSet<>();

    public PolicySpecificationImpl(final String policyName) {
        this.policyName = policyName;
    }

    @Override
    public String getPolicyName() {
        return policyName;
    }

    @Override
    public Set<Column<? extends Cell>> getColumns() {
        return columns;
    }

    @Override
    public Set<PolicyHierarchy<? extends Cell>> getHierarchies() {
        return hierarchies;
    }

    @Override
    public PolicyFilter getFilter(PolicyEngine policyEngine) {
        // TODO - need to implement filtering
        return () -> (Specification) (root, criteriaQuery, criteriaBuilder) -> null;
    }

    @Override
    public List<SortingInfo> getSorters() {
        return new ArrayList<>();
    }

    @Override
    public List<GroupingInfo> getGroupingCriteria() {
        return new ArrayList<>();
    }
}
