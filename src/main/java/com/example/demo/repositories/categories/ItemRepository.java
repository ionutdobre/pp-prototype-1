package com.example.demo.repositories.categories;

import com.example.demo.persistence.Item;
import com.example.demo.repositories.TextSearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author idobre
 * @since 17/11/2019
 */
@Transactional
public interface ItemRepository extends TextSearchableRepository<Item, Long> {
    @Override
    @Query("select item from Item item where lower(item.description) like %?1%")
    Page<Item> searchText(String description, Pageable page);
}
