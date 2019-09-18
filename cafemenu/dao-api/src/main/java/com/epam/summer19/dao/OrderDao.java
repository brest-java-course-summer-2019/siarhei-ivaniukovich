package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import com.epam.summer19.dto.OrderDTO;

import java.time.LocalDateTime;
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
     * Get all orders DTOs (summary price+items)
     * @return
     */
    List<OrderDTO> findAllDTO();

    /**
     * Find order by orderId
     * @param orderId
     * @return
     */
    Optional<Order> findOrderById(Integer orderId);

    /**
     * Find orders via OrderDTO object (by price SUM, items Q and order by Date & Time)
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
