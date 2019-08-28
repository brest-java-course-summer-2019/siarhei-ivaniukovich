package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.model.ItemInOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ItemInOrderServiceImpl implements ItemInOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInOrderServiceImpl.class);
    private ItemInOrderDao itemInOrderDao;

    public ItemInOrderServiceImpl(ItemInOrderDao itemInOrderDao) {
        this.itemInOrderDao = itemInOrderDao;
    }

    @Override
    public void add(ItemInOrder... iteminorders) {
        for(ItemInOrder iteminorder : iteminorders) {
            itemInOrderDao.add(iteminorder);
        }
    }

    @Override
    public void add(ItemInOrder iteminorder) {
        LOGGER.debug("Single IntemInOrder add({})", iteminorder);
        itemInOrderDao.add(iteminorder);
    }

    @Override
    public void update(ItemInOrder iteminorder) {
        LOGGER.debug("update({})", iteminorder);
        itemInOrderDao.update(iteminorder);
    }

    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("delete({})", iioOrderId+' '+iioItemId);
        itemInOrderDao.delete(iioOrderId, iioItemId);
    }

    @Override
    public List<ItemInOrder> findAll() {
        LOGGER.debug("Find all ItemInOrders");
        return itemInOrderDao.findAll();
    }

    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        LOGGER.debug("Find iteminorder by iioOrderId");
        return itemInOrderDao.findIioByOrderId(iioOrderId);
    }

    @Override
    public ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("Find iteminorder by iioOrderId & iioItemId");
        return itemInOrderDao.findIioByOrderItemId(iioOrderId, iioItemId)
                .orElseThrow(() -> new RuntimeException("Failed to get item from DB"));
   }
}
