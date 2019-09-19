package com.epam.summer19.model;

import java.time.LocalDateTime;

public class Order {

    /**
     * order id
     */
    private Integer orderId;

    /**
     * order maked Employee id
     */
    private Integer orderEmployeeId;

    /**
     * Order Time
     */
    private LocalDateTime orderDateTime;

    /**
     * Get Order ID
     * @return
     */
    public Integer getOrderId() { return orderId; }

    /**
     * Set Order ID
     * @param orderId
     */
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getOrderEmployeeId() {
        return orderEmployeeId;
    }

    public void setOrderEmployeeId(Integer orderEmployeeId) {
        this.orderEmployeeId = orderEmployeeId;
    }

    public LocalDateTime getOrderDateTime() { return orderDateTime; }

    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }
}
