package com.epam.summer19.dao;

import com.epam.summer19.model.ItemInOrder;
import java.util.List;
import java.util.Optional;

public interface ItemInOrderDao {

    /**
     * Create new ItemInOrder
     * @param iteminorder
     * @return
     */
    ItemInOrder add(ItemInOrder iteminorder);

    /**
     * Update ItemInOrder
     * @param iteminorder
     */
    void update(ItemInOrder iteminorder);

    /**
     * Delete ItemInOrder
     * @param itemInOrderId
     */
    void delete(Integer iioOrderId, Integer iioItemId);

    /**
     * Get all item in order
     * @return
     */
    List<ItemInOrder> findAll();

    /**
     * Finditem in order by orderId
     * @param orderId
     * @return
     */
    Optional<ItemInOrder> findIioByOrderId(Integer iioOrderId);

    /**
     * Finditem in order by orderId
     * @param orderId
     * @return
     */
    Optional<ItemInOrder> findIioByOrderItemId(Integer iioOrderId, Integer iioItemId);

}
