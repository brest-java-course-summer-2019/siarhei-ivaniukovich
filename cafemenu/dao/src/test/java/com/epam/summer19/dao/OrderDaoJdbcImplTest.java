package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import com.epam.summer19.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class OrderDaoJdbcImplTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void add() {
        // REWRITE TEST WHEN MAP INTEGRATION DONE!!!
        Order testOrder = new Order();
        testOrder.setOrderId(1);
        // MAP!!!
        Order newOrder = orderDao.add(testOrder);
        assertEquals(new Integer(1),newOrder.getOrderId());
    }

    @Test
    public void update() {
        Order testOrder = new Order();
        testOrder.setOrderId(1);
        Order newOrder = orderDao.add(testOrder);
        newOrder.setOrderId(2);
        orderDao.update(newOrder);
        // MAP!!!
        Order updatedOrder = orderDao.findOrderById(newOrder.getOrderId()).get();
        assertTrue(newOrder.getOrderId().equals(updatedOrder.getOrderId()));
    }

    @Test
    public void delete() {
        Order testOrder = new Order();
        testOrder.setOrderId(1);
        testOrder = orderDao.add(testOrder);
        List<Order> items = orderDao.findAll();
        int sizeBefore = items.size();
        orderDao.delete(testOrder.getOrderId());
        assertTrue((sizeBefore - 1) == orderDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<Order> orders = orderDao.findAll();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

    @Test
    public void findOrderById() {
        Integer orderId = 1;
        Order testOrder = new Order();
        testOrder.setOrderId(orderId);
        // MAP
        testOrder = orderDao.add(testOrder);
        Order findOrder = orderDao.findOrderById(orderId).get();
        assertNotNull(findOrder);
        assertTrue(findOrder.getOrderId().equals(orderId));
        assertEquals(testOrder.getOrderId(), findOrder.getOrderId());
    }

}