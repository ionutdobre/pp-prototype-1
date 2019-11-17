package com.example.demo.repositories.categories;

import com.example.demo.persistence.categories.Category;
import com.example.demo.repositories.TextSearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository<T extends Category> extends TextSearchableRepository<T, Long> {

    @Override
    @Query("select cat from  #{#entityName} cat where lower(cat.label) like %:code%")
    Page<T> searchText(@Param("code") String code, Pageable page);
}
