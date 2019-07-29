package com.epam.summer19.model;

import java.time.LocalDateTime;

public class Order {

    /**
     * order id
     */
    private Integer orderId;

    /**
     * private Map<Integer,String,BigDecimal> orderItems;
     *
     * private Integer orderItemsCount;
     *
     * private BigDecimal order
    **/

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


}