package com.epam.summer19.model;

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
     *  order status param
     */
    private Integer orderStatus;

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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
