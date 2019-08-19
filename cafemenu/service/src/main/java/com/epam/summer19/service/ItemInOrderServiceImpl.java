package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.model.ItemInOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ItemInOrderServiceImpl implements ItemInOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInOrderServiceImpl.class);
    private ItemInOrderDao iiodao;

    public ItemInOrderServiceImpl(ItemInOrderDao iiodao) {
        this.iiodao = iiodao;
    }

    @Override
    public void add(ItemInOrder... iteminorders) {
        for(ItemInOrder iteminorder : iteminorders) {
            iiodao.add(iteminorder);
        }
    }

    @Override
    public void update(ItemInOrder iteminorder) {
        LOGGER.debug("update({})", iteminorder);
        iiodao.update(iteminorder);
    }

    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("delete({})", iioOrderId+' '+iioItemId);
        iiodao.delete(iioOrderId, iioItemId);
    }

    @Override
    public List<ItemInOrder> findAll() {
        LOGGER.debug("Find all ItemInOrders");
        return iiodao.findAll();
    }

    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        LOGGER.debug("Find iteminorder by iioOrderId");
        return iiodao.findIioByOrderId(iioOrderId);
    }

    @Override
    public ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("Find iteminorder by iioOrderId & iioItemId");
        return iiodao.findIioByOrderItemId(iioOrderId, iioItemId)
                .orElseThrow(() -> new RuntimeException("Failed to get item from DB"));
   }

}
