package com.epam.summer19;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit test for simple App.
 */
public class testingOrders
{

    Orders orders = new Orders();


   @Test
   public void getEntityId() {
       orders.setEntityId(2);
       Assert.assertTrue(orders.getEntityId().equals(15));
   }

    @Test
    public void getEntityName() {
        orders.setEntityName("Entity");
        Assert.assertTrue(orders.getEntityId().equals("Entity"));
    }

    @Test
    public void getEntityCount() {
        orders.setEntityCount(new BigDecimal("5.0"));
        Assert.assertTrue(orders.getEntityCount().compareTo(new BigDecimal("5.0")) == 0);
    }

}
