package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;


/**
 * model test for Cafe Menu.
 */
public class OrderTest {

    Order order = new Order();

    @Test
    public void getOrderId() {
        order.setOrderId(4);
        Assert.assertTrue(order.getOrderId().equals(4));
    }

}
