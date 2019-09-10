package com.epam.summer19.service;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * Create new multiple orders
     *
     * @param orders
     * @return
     */
    void add(Order... orders);

    /**
     * Create new order
     *
     * @param order
     * @return
     */
    void add(Order order);

    /**
     * Update order
     *
     * @param order
     */
    void update(Order order);

    /**
     * Delete order
     *
     * @param orderId
     */
    void delete(Integer orderId);

    /**
     * Get all orders
     *
     * @return
     */
    List<Order> findAll();

    /**
     * Get all orders DTO
     *
     * @return
     */
    List<OrderDTO> findAllDTO();

    /**
     * Find order by orderId
     *
     * @param orderId
     * @return
     */
    Order findOrderById(Integer orderId);

    /**
     * Find orders by Date and Time set
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);
}