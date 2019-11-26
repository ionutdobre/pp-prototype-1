package com.example.demo.policy;

import com.example.demo.persistence.Item;
import com.example.demo.policy.behaviour.Behaviour;
import com.example.demo.policy.cell.Cell;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class SqlPolicyColumn<K extends Cell> extends PolicyColumn<K> {
    final Function valueGetter;

    // TODO - for now we are using "Function<Item, Object>" - try to make this more generic
    public SqlPolicyColumn(final Entity entity,
                           final List<Behaviour<K>> behaviours,
                           final Function<Item, Object> valueGetter) {
        super(entity, behaviours);
        this.valueGetter = valueGetter;
    }

    @Override
    public List<K> calculateValues() {
        return (List<K>) getEntity().getElements().stream().map(element -> valueGetter.apply(element)).collect(Collectors.toList());
    }
}
