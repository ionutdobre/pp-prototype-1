package com.example.demo.services;

import com.example.demo.persistence.categories.Role;

public interface RoleService extends BaseJpaService<Role>, TextSearchableService<Role> {

}
