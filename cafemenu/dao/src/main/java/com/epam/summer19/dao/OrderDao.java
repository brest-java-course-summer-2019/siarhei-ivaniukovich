package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

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
    Optional<Order> findOrderById(Integer orderId);
}
