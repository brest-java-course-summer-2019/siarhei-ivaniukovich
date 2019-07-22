package com.epam.summer19.model;

import java.time.LocalDateTime;

public class OrdersList {
    /**
     * order id
     */
    private Integer orderId;

    /**
     * order maked Employee id
     */
    private Integer orderEmployeeId;

    /**
     * order time
     */
    private LocalDateTime orderTime;

    /**
     *  order status param
     */
    private Integer orderStatus;

    /**
     * Getters and setters next
     * @return
     */
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderEmployeeId() {
        return orderEmployeeId;
    }

    public void setOrderEmployeeId(Integer orderEmployeeId) {
        this.orderEmployeeId = orderEmployeeId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
