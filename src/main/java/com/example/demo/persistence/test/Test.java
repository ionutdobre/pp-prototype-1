package com.example.demo.persistence.test;

import com.example.demo.persistence.GenericPersistable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author idobre
 * @since 14/11/2019
 */
@Entity
@Table(indexes = {@Index(columnList = "label")})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Test extends GenericPersistable {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("Test[id=%d, name='%s']", this.getId(), this.getLabel());
    }
}
