package com.epam.summer19.dao;

import com.epam.summer19.model.Item;

import java.util.List;

public interface ItemDao {

    Item add(Item item);

    void update(Item item);

    void delete(Integer itemId);

    List<Item> findAll();

}
