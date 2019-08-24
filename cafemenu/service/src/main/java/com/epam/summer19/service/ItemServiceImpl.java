package com.epam.summer19.service;

import com.epam.summer19.dao.ItemDao;
import com.epam.summer19.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    private ItemDao itemdao;

    public ItemServiceImpl(ItemDao itemdao) {
        this.itemdao = itemdao;
    }

    @Override
    public void add(Item... items) {
        LOGGER.debug("multiple items add({})");
        for(Item item : items) {
            itemdao.add(item);
        }
    }

    @Override
    public void add(Item item) {
        LOGGER.debug("single item add({})", item);
        itemdao.add(item);
    }

    @Override
    public void update(Item item) {
        LOGGER.debug("item update({})", item);
        itemdao.update(item);
    }

    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("item delete({})", itemId);
        itemdao.delete(itemId);
    }

    @Override
    public List<Item> findAll() {
        LOGGER.debug("Find all Items");
        return itemdao.findAll();
    }

    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Find item by itemId: findItemById({})", itemId);
        return itemdao.findItemById(itemId)
                .orElseThrow(() ->  new RuntimeException("Failed to get items from DB"));
    }

}
