package com.example.demo.services;

import com.example.demo.persistence.categories.Role;
import com.example.demo.repositories.BaseJpaRepository;
import com.example.demo.repositories.TextSearchableRepository;
import com.example.demo.repositories.categories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author idobre
 * @since 2019-03-04
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseJpaServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected BaseJpaRepository<Role, Long> repository() {
        return roleRepository;
    }

    @Override
    public TextSearchableRepository<Role, Long> textRepository() {
        return roleRepository;
    }

    @Override
    public Role newInstance() {
        return new Role();
    }
}
