package com.example.demo.persistence;

import com.example.demo.persistence.categories.Brand;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author idobre
 * @since 17/11/2019
 */
@Entity
@Table(indexes = {@Index(columnList = "brand_id")})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item extends GenericPersistable {
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    private Brand brand;

    private String description;

    private Double currentPrice;

    // currency

    private Double futurePrice;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(final Brand brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
