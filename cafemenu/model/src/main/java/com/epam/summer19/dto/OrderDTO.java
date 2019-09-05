package com.epam.summer19.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO {

    /**
     * order id
     */
    private Integer orderId;

    /**
     * order maked Employee id
     */
    private Integer employeeId;

    /**
     * Order Time
     */
    private LocalDateTime orderDateTime;

    /**
     * Order Summary price
     */
    private BigDecimal summaryPrice;

    /**
     * Number of items in order
     */
    private Integer itemsQuantity;

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }

    public BigDecimal getSummaryPrice() { return summaryPrice; }
    public void setSummaryPrice(BigDecimal summaryPrice) { this.summaryPrice = summaryPrice; }

    public Integer getItemsQuantity() { return itemsQuantity; }
    public void setItemsQuantity(Integer itemsQuantity) { this.itemsQuantity = itemsQuantity; }
}
