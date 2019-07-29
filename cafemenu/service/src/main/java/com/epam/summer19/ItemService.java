package com.epam.summer19.service;

import com.epam.summer19.model.Item;
import java.util.List;

public interface ItemService {

    /**
     * Find All Items
     * @return
     */
    List<Item> findAll();

    /**
     * Update Items
     * @param item
     */
    void update(Item item);

    /**
     * Delete Item
     * @param itemId
     */
    void delete(Integer itemId);
}