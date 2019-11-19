package com.example.demo.services;

import com.example.demo.persistence.Item;
import com.example.demo.repositories.BaseJpaRepository;
import com.example.demo.repositories.TextSearchableRepository;
import com.example.demo.repositories.categories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author idobre
 * @since 17/11/2019
 */
@Service
@Transactional(readOnly = true)
public class ItemServiceImpl extends BaseJpaServiceImpl<Item> implements ItemService {
    @Autowired
    private ItemRepository repository;

    @Override
    protected BaseJpaRepository<Item, Long> repository() {
        return repository;
    }

    @Override
    public TextSearchableRepository<Item, Long> textRepository() {
        return repository;
    }

    @Override
    public Item newInstance() {
        return new Item();
    }
}
