package com.epam.summer19.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * model test for Cafe Menu.
 */
public class ItemTest {

    private Item item = new Item();

    @Test
    public void getItemId() {
        item.setItemId(24);
        Assert.assertTrue(item.getItemId().equals(24));
    }

    @Test
    public void getItemName() {
        item.setItemName("Item");
        Assert.assertTrue(item.getItemName().equals("Item"));
    }

    @Test
    public void getItemPrice() {
        item.setItemPrice(new BigDecimal("5.0"));
        Assert.assertTrue(item.getItemPrice().compareTo(new BigDecimal("5.0")) == 0);
    }

}
