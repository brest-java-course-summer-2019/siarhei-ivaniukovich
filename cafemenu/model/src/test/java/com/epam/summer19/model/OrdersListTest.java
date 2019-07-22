package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * model test for Cafe Menu.
 */
public class OrdersListTest {

    OrdersList ordersList = new OrdersList();

    @Test
    public void getOrderId() {
        ordersList.setOrderId(15);
        Assert.assertTrue(ordersList.getOrderId().equals(15));
    }

    @Test
    public void getOrderEmployeeId() {
        ordersList.setOrderEmployeeId(2);
        Assert.assertTrue(ordersList.getOrderEmployeeId().equals(2));
    }

    @Test
    public void getOrderTime() {
        ordersList.setOrderTime(LocalDateTime.of(2019,7,22,16,05));
        Assert.assertTrue(ordersList.getOrderTime().equals(LocalDateTime.of(2019,7,22,16,05)));
    }

    @Test
    public void getOrderStatus() {
        ordersList.setOrderStatus(1);
        Assert.assertTrue(ordersList.getOrderStatus().equals(1));
    }

}
