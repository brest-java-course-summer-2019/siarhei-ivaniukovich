package com.epam.summer19.service;

import com.epam.summer19.model.ItemInOrder;

import java.util.List;

public interface ItemInOrderService {

    /**
     * Create new ItemInOrder
     * @param iteminorders
     * @return
     */
    void add(ItemInOrder... iteminorders);

    /**
     * Update ItemInOrder
     * @param iteminorder
     */
    void update(ItemInOrder iteminorder);

    /**
     * Delete ItemInOrder
     * @param iioOrderId, iioItemId
     */
    void delete(Integer iioOrderId, Integer iioItemId);

    /**
     * Get all item in order
     * @return
     */
    List<ItemInOrder> findAll();

    /**
     * Finditem in order by orderId
     * @param iioOrderId
     * @return
     */
    List<ItemInOrder> findIioByOrderId(Integer iioOrderId);

    /**
     * Finditem in order by orderId
     * @param iioOrderId, iioItemId
     * @return
     */
    ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId);
}
