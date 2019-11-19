package com.example.demo.services;

import com.example.demo.persistence.Item;

/**
 * @author idobre
 * @since 17/11/2019
 */
public interface ItemService extends BaseJpaService<Item>, TextSearchableService<Item> {

}
