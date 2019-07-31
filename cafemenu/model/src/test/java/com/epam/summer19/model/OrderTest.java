package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * model test for Cafe Menu.
 */
public class OrderTest {

    Order order = new Order();
    Map<Integer, Integer> orderItems = Map.of(1,2);

    @Test
    public void getOrderId() {
        order.setOrderId(4);
        Assert.assertTrue(order.getOrderId().equals(4));
    }

    @Test
    public void getOrderItems() {
        order.setOrderItems(orderItems);
        Assert.assertTrue(order.getOrderItems().equals(orderItems));
    }

}
