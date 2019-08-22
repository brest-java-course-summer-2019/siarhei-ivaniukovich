package com.epam.summer19.service;

import com.epam.summer19.model.Order;

import java.util.List;

public interface OrderService {

    /**
     * Create new multiple orders
     * @param orders
     * @return
     */
    void add(Order... orders);

    /**
     * Create new order
     * @param order
     * @return
     */
    Order add(Order order);

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