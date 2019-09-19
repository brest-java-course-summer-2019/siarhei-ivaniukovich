package com.epam.summer19.service;

import com.epam.summer19.model.ItemInOrder;

import java.util.List;

public interface ItemInOrderService {

    /**
     * Create new single ItemInOrder
     * @param iteminorder
     * @return
     */
    void add(ItemInOrder iteminorder);


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
     * Get all ItemInOrder's
     * @return
     */
    List<ItemInOrder> findAll();

    /**
     * Find ItemInOrder by orderId
     * @param iioOrderId
     * @return
     */
    List<ItemInOrder> findIioByOrderId(Integer iioOrderId);

    /**
     * Find ItemInOrder by orderId & itemId
     * @param iioOrderId, iioItemId
     * @return
     */
    ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId);
}
