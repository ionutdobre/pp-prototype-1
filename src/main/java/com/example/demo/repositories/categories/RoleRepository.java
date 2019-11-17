package com.example.demo.repositories.categories;

import com.example.demo.persistence.categories.Role;
import com.example.demo.repositories.TextSearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepository extends TextSearchableRepository<Role, Long> {

    @Override
    @Query("select role from  Role role where lower(role.authority) like %?1%")
    Page<Role> searchText(String code, Pageable page);
}
