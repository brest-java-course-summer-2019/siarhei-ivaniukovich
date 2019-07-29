package com.epam.summer19.dao;

import com.epam.summer19.model.OrdersList;
import java.util.List;
import java.util.Optional;

public interface OrdersListDao {


    /**
     * Create new orderslist
     * @param orderslist
     * @return
     */
    OrdersList add(OrdersList orderslist);

    /**
     * Update orderslist
     * @param orderslist
     */
    void update(OrdersList orderslist);

    /**
     * Delete orderslist
     * @param orderslistId
     */
    void delete(Integer orderslistId);

    /**
     * Get all orderslists
     * @return
     */
    List<OrdersList> findAll();

    /**
     * Get all orderslists by OrdersListId
     * @param orderId
     * @return
     */
    Optional<OrdersList> findByOrderId(Integer orderId);

    /**
     * Get all orderslists by EmployeeId
     * @param employeeId
     * @return
     */
    Optional<OrdersList> findByEmployeeId(Integer employeeId);

}
