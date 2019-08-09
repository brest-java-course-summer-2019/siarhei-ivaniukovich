package com.epam.summer19.dao;

import com.epam.summer19.model.OrdersList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersListDaoTest {

    @Autowired
    OrdersListDao orderslistDao;

    @Test
    public void add() {
        OrdersList testOrdersList = new OrdersList();
        testOrdersList.setOrderId(1);
        testOrdersList.setEmployeeId(5);
        testOrdersList.setOrderStatus(2);
        OrdersList newOrdersList = orderslistDao.add(testOrdersList);
        assertEquals(new Integer(1),newOrdersList.getOrderId());
        assertEquals(new Integer(5),newOrdersList.getEmployeeId());
        assertEquals(new Integer(2),newOrdersList.getOrderStatus());
    }

    @Test
    public void update() {
        OrdersList testOrdersList = new OrdersList();
        testOrdersList.setOrderId(1);
        testOrdersList.setEmployeeId(5);
        testOrdersList.setOrderStatus(2);
        OrdersList newOrdersList = orderslistDao.add(testOrdersList);
        newOrdersList.setOrderId(1);
        newOrdersList.setEmployeeId(5);
        newOrdersList.setOrderStatus(2);
        orderslistDao.update(newOrdersList);
        OrdersList updatedOrdersList = orderslistDao.findByOrderId(newOrdersList.getOrderId()).get();
        assertTrue(newOrdersList.getOrderId().equals(updatedOrdersList.getOrderId()));
        assertTrue(newOrdersList.getEmployeeId().equals(updatedOrdersList.getEmployeeId()));
        assertTrue(newOrdersList.getOrderStatus().equals(updatedOrdersList.getOrderStatus()));

    }

    @Test
    public void delete() {
        OrdersList testOrdersList = new OrdersList();
        testOrdersList.setOrderId(1);
        testOrdersList.setEmployeeId(5);
        testOrdersList.setOrderStatus(2);
        testOrdersList = orderslistDao.add(testOrdersList);
        List<OrdersList> orderslist = orderslistDao.findAll();
        int sizeBefore = orderslist.size();
        orderslistDao.delete(testOrdersList.getOrderId());
        assertTrue((sizeBefore - 1) == orderslistDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<OrdersList> orderslist = orderslistDao.findAll();
        assertNotNull(orderslist);
        assertTrue(orderslist.size() > 0);
    }

    @Test
    public void findByOrderId() {
        Integer orderId = 1;
        OrdersList testOrdersList = new OrdersList();
        testOrdersList.setOrderId(1);
        testOrdersList.setEmployeeId(5);
        testOrdersList.setOrderStatus(2);
        testOrdersList = orderslistDao.add(testOrdersList);
        OrdersList findOrdersList = orderslistDao.findByOrderId(orderId).get();
        assertNotNull(findOrdersList);
        assertTrue(findOrdersList.getOrderId().equals(orderId));
        assertEquals(testOrdersList.getOrderId(), findOrdersList.getOrderId());
        assertEquals(testOrdersList.getEmployeeId(), findOrdersList.getEmployeeId());
        assertEquals(testOrdersList.getOrderStatus(), findOrdersList.getOrderStatus());
    }

    @Test
    public void findByEmployeeId() {
        Integer employeeId = 5;
        OrdersList testOrdersList = new OrdersList();
        testOrdersList.setOrderId(1);
        testOrdersList.setEmployeeId(5);
        testOrdersList.setOrderStatus(2);
        testOrdersList = orderslistDao.add(testOrdersList);
        OrdersList findOrdersList = orderslistDao.findByOrderId(employeeId).get();
        assertNotNull(findOrdersList);
        assertTrue(findOrdersList.getOrderId().equals(employeeId));
        assertEquals(testOrdersList.getOrderId(), findOrdersList.getOrderId());
        assertEquals(testOrdersList.getEmployeeId(), findOrdersList.getEmployeeId());
        assertEquals(testOrdersList.getOrderStatus(), findOrdersList.getOrderStatus());
    }
}