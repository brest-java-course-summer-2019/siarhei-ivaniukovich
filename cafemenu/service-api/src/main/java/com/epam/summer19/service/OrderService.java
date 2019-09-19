package com.epam.summer19.service;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * Create new single Order
     *
     * @param order
     * @return
     */
    Order add(Order order);

    /**
     * Update Order
     *
     * @param order
     */
    void update(Order order);

    /**
     * Delete Order
     *
     * @param orderId
     */
    void delete(Integer orderId);

    /**
     * List all Order's
     *
     * @return
     */
    List<Order> findAll();

    /**
     * List all OrderDTO objects
     *
     * @return
     */
    List<OrderDTO> findAllDTO();

    /**
     * Find Order by orderId
     *
     * @param orderId
     * @return
     */
    Order findOrderById(Integer orderId);

    /**
     * Find all OrderDTO's by Date and Time set
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);
}