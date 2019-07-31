package com.epam.summer19.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {

    /**
     * order id
     */
    private Integer orderId;

    /**
     * orderItems - MAP of items (item ID, count of this item)
     */
    private Map<Integer,Integer> orderItems;

    /**
     * Get Order ID
     * @return
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Set Order ID
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Get list of items from order
     * @return
     */
    public Map<Integer, Integer> getOrderItems() {
        return orderItems;
    }

    /**
     * Set list of items from order
     * @param orderItems
     */
    public void setOrderItems(Map<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
    }
}