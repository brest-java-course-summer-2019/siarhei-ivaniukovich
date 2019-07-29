package com.epam.summer19.service;

import com.epam.summer19.model.Order;
import java.util.List;

public interface OrderService {

    /**
     * Find All Orders
     * @return
     */
    List<Order> findAll();

    /**
     * Update OrdersList
     * @param order
     */
    void update(Order order);

    /**
     * Delete OrdersList
     * @param orderId
     */
    void delete(Integer orderId);
}