package com.epam.summer19;

import com.epam.summer19.model.Item;
import java.util.List;

public class ItemDao {

    Item add(Item item);

    void update(Item item);

    void delete(Integer itemId);

    List<Item> findAll();
}
