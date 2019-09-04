package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.dao.OrderDao;
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
    private OrderDao orderDao;

    public ItemInOrderServiceImpl(ItemInOrderDao itemInOrderDao, OrderDao orderDao) {
        this.itemInOrderDao = itemInOrderDao;
        this.orderDao = orderDao;
    }

    @Override
    public void add(ItemInOrder... iteminorders) {
        for(ItemInOrder iteminorder : iteminorders) {
            itemInOrderDao.add(iteminorder);
            //orderDao.calcSummaryOrderPrice(iteminorder.getIioOrderId());
        }
    }

    @Override
    public void add(ItemInOrder iteminorder) {
        LOGGER.debug("Single ItemInOrder add({})", iteminorder);
        itemInOrderDao.add(iteminorder);
        LOGGER.debug("Single ItemInOrder add({}) - orderDao.calcSummaryOrderPrice({})", iteminorder, iteminorder.getIioOrderId());
        orderDao.calcSummaryOrderPrice(iteminorder.getIioOrderId());
    }

    @Override
    public void update(ItemInOrder iteminorder) {
        LOGGER.debug("ItemInOrder update({})", iteminorder);
        itemInOrderDao.update(iteminorder);
        LOGGER.debug("ItemInOrder update({}) - orderDao.calcSummaryOrderPrice({})", iteminorder, iteminorder.getIioOrderId());
        orderDao.calcSummaryOrderPrice(iteminorder.getIioOrderId());
    }

    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("ItemInOrder delete({})", iioOrderId+','+iioItemId);
        itemInOrderDao.delete(iioOrderId, iioItemId);
        LOGGER.debug("ItemInOrder delete({}) - orderDao.calcSummaryOrderPrice({})", iioOrderId+','+iioItemId, iioOrderId);
        orderDao.calcSummaryOrderPrice(iioOrderId);
    }

    @Override
    public List<ItemInOrder> findAll() {
        LOGGER.debug("Find all ItemInOrders");
        return itemInOrderDao.findAll();
    }

    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        LOGGER.debug("Find ItemInOrder by iioOrderId={}", iioOrderId);
        return itemInOrderDao.findIioByOrderId(iioOrderId);
    }

    @Override
    public ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("Find ItemInOrder by iioOrderId={} & iioItemId={}", iioOrderId, iioItemId);
        return itemInOrderDao.findIioByOrderItemId(iioOrderId, iioItemId)
                .orElseThrow(() -> new RuntimeException("Failed to get item from DB"));
   }
}
