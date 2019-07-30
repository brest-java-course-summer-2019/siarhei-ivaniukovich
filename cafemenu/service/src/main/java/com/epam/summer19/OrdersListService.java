package com.epam.summer19.service;

import com.epam.summer19.model.OrdersList;
import java.util.List;

public interface OrdersListService {

    /**
     * Find All OrdersList
     * @return
     */
    List<OrdersList> findAll();

    /**
     * Find All OrderList by orderId
     * @param orderId
     * @return
     */
    OrdersList findByOrderId(Integer orderId);

    /**
     * Find All OrdersList by employeeId
     * @param employeeId
     * @return
     */
    OrdersList findByEmployeeId(Integer employeeId);

    /**
     * Update OrdersList
     * @param orderslist
     */
    void update(OrdersList orderslist);

    /**
     * Delete OrdersList
     * @param orderId
     */
    void delete(Integer orderId);
}