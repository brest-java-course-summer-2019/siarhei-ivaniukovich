package com.epam.summer19.dao;

import com.epam.summer19.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    /**
     * Add item
     * @param item
     * @return
     */
    Item add(Item item);

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
    Optional<Item> findItemById(Integer itemId);

    /**
     * Find item by itemName
     * @param itemName
     * @return
     */
    Optional<Item> findItemByName(String itemName);
}
