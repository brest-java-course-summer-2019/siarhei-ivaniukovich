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
    private ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void add(Item... items) {
        LOGGER.debug("Service: Multiple Items add(...)");
        for(Item item : items) {
            itemDao.add(item);
        }
    }

    @Override
    public void add(Item item) {
        LOGGER.debug("Service: Single Item add({})", item);
        itemDao.add(item);
    }

    @Override
    public void update(Item item) {
        LOGGER.debug("Service: Item update({})", item);
        itemDao.update(item);
    }

    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("Service: Item delete({})", itemId);
        itemDao.delete(itemId);
    }

    @Override
    public List<Item> findAll() {
        LOGGER.debug("Service: Find all Items");
        return itemDao.findAll();
    }

    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Service: Find Item by itemId:{}", itemId);
        return itemDao.findItemById(itemId)
                .orElseThrow(() -> new RuntimeException("Failed to get items from DB"));
    }

    @Override
    public Item findItemByName(String itemName) {
        LOGGER.debug("Service: Find item by itemName:{}", itemName);
        return itemDao.findItemByName(itemName)
                .orElse(null);
    }
}
