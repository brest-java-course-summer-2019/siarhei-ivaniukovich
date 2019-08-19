package com.epam.summer19.service;

import com.epam.summer19.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    /**
     * Add item
     * @param item
     * @return
     */
    void add(Item... items);

    /**
     * Update item
     * @param item
     */
    void update(Item item);

    /**
     * Delete item by id
     * @param itemId
     */
    void delete(Integer itemId);

    /**
     * List all items
     * @return
     */
    List<Item> findAll();

    /**
     * Find item by itemId
     * @param itemId
     * @return
     */
    Item findItemById(Integer itemId);
}