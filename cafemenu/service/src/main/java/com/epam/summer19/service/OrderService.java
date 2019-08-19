package com.epam.summer19.service;

import com.epam.summer19.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    /**
     * Create new order
     * @param order
     * @return
     */
    void add(Order... orders);

    /**
     * Update order
     * @param order
     */
    void update(Order order);

    /**
     * Delete order
     * @param orderId
     */
    void delete(Integer orderId);

    /**
     * Get all orders
     * @return
     */
    List<Order> findAll();

    /**
     * Find order by orderId
     * @param orderId
     * @return
     */
    Order findOrderById(Integer orderId);
}