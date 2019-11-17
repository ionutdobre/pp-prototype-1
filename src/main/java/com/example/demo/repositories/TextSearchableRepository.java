package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author idobre
 */
@NoRepositoryBean
@Transactional
public interface TextSearchableRepository<T, ID extends Serializable> extends BaseJpaRepository<T, ID> {
    @Override
    Page<T> findAll(Pageable pageable);

    Page<T> searchText(String code, Pageable page);
}
