package com.epam.summer19.service;

import com.epam.summer19.dao.ItemDao;
import com.epam.summer19.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    private ItemDao itemdao;

    public ItemServiceImpl(ItemDao itemdao) {
        this.itemdao = itemdao;
    }

    @Override
    public List<ItemDao> findAll() {
        LOGGER.debug("Find all Items");
        return itemdao.findAll();
    }

    @Override
    public void update(Item item) {
        LOGGER.debug("update({})", item);
        itemdao.update(item);
    }

    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("delete({})", itemId);
        itemdao.delete(itemId);
    }

}
