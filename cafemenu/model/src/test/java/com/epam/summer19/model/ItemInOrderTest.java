package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemInOrderTest {
    ItemInOrder iteminorder = new ItemInOrder();

    @Test
    public void getIioOrderId() {
        iteminorder.setIioOrderId(3);
        Assert.assertTrue(iteminorder.getIioOrderId().equals(3));
    }

    @Test
    public void getIioItemId() {
        iteminorder.setIioItemId(17);
        Assert.assertTrue(iteminorder.getIioItemId().equals(17));
    }

    @Test
    public void getIioItemName() {
        iteminorder.setIioItemName("Burger");
        Assert.assertTrue(iteminorder.getIioItemName().equals("Burger"));
    }

    @Test
    public void getIioItemPrice() {
        iteminorder.setIioItemPrice(new BigDecimal("3.0"));
        Assert.assertTrue(iteminorder.getIioItemPrice().compareTo(new BigDecimal("3.0")) == 0);
    }

    @Test
    public void getIioItemCount() {
        iteminorder.setIioItemCount(4);
        Assert.assertTrue(iteminorder.getIioItemCount().equals(4));
    }
}
