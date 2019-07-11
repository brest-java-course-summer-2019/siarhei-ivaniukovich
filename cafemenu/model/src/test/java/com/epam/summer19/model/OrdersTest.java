package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * model test for Cafe Menu.
 */
public class OrdersTest {

    Orders orders = new Orders();

    @Test
    public void getEntityId() {
        orders.setEntityId(15);
        Assert.assertTrue(orders.getEntityId().equals(15));
    }

    @Test
    public void getEntityName() {
        orders.setEntityName("Entity");
        Assert.assertTrue(orders.getEntityName().equals("Entity"));
    }

    @Test
    public void getEntityCount() {
        orders.setEntityCount(new BigDecimal("5.0"));
        Assert.assertTrue(orders.getEntityCount().compareTo(new BigDecimal("5.0")) == 0);
    }

}
