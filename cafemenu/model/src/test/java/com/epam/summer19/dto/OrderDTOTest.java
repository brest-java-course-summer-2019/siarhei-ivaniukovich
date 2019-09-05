package com.epam.summer19.dto;

import com.epam.summer19.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * model test for Cafe Menu.
 */
public class OrderDTOTest {

    private OrderDTO orderDTO = new OrderDTO();

    @Test
    public void getOrderId() {
        orderDTO.setOrderId(4);
        Assert.assertTrue(orderDTO.getOrderId().equals(4));
    }

    @Test
    public void getOrderEmployeeId() {
        orderDTO.setEmployeeId(2);
        Assert.assertTrue(orderDTO.getEmployeeId().equals(2));
    }

    @Test
    public void getOrderDateTime() {
        LocalDateTime datetime = LocalDateTime.now();
        orderDTO.setOrderDateTime(datetime);
        Assert.assertTrue(orderDTO.getOrderDateTime().equals(datetime));
    }

    @Test
    public void getSummaryPrice() {
        orderDTO.setSummaryPrice(new BigDecimal("22.0"));
        Assert.assertTrue(orderDTO.getSummaryPrice().compareTo(new BigDecimal("22.0")) == 0);
    }

    @Test
    public void getItemsQuantity() {
        orderDTO.setItemsQuantity(7);
        Assert.assertTrue(orderDTO.getItemsQuantity().equals(7));
    }
}
