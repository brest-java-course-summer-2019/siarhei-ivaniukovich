package com.epam.summer19.model;

public class OrdersList {
    /**
     * order id
     */
    private Integer orderId;

    /**
     * order maked Employee id
     */
    private Integer employeeId;

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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
