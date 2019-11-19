package com.example.demo.persistence.categories;

import com.example.demo.persistence.GenericPersistable;
import com.example.demo.persistence.Labelable;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author idobre
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(length = Category.DTYPE_COLUMN_LENGTH)
@Table(indexes = {@Index(columnList = "label"), @Index(columnList = "DTYPE")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends GenericPersistable implements Serializable, Labelable {
    static final int DTYPE_COLUMN_LENGTH = 100;

    private String label;

    private String description;

    private String code;

    public Category() {

    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
