package com.example.demo.persistence.categories;

import javax.persistence.Entity;

/**
 * @author idobre
 * @since 17/11/2019
 */
@Entity
public class Role extends Category {
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }
}
