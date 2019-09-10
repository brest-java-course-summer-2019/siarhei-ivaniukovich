package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import com.epam.summer19.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class OrderDaoJdbcImplTest {

    private static final Integer EMPLOYEE_ID = 21;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ItemInOrderDao itemInOrderDao;

    @Test
    public void add() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        Order newOrder = orderDao.add(testOrder);
        assertNotNull(newOrder.getOrderId());
        assertEquals(new Integer(EMPLOYEE_ID),newOrder.getOrderEmployeeId());
        assertNotNull(newOrder.getOrderDateTime());
    }

    @Test
    public void update() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder = orderDao.add(testOrder);
        testOrder.setOrderEmployeeId(22);
        orderDao.update(testOrder);
        Order updatedOrder = orderDao.findOrderById(testOrder.getOrderId()).get();
        assertTrue(testOrder.getOrderId().equals(updatedOrder.getOrderId()));
        assertTrue(testOrder.getOrderEmployeeId().equals(updatedOrder.getOrderEmployeeId()));
    }

    @Test
    public void delete() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder = orderDao.add(testOrder);
        List<Order> orders = orderDao.findAll();
        int sizeBefore = orders.size();
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
    public void findAllDTO() {
        List<OrderDTO> orderDTOs = orderDao.findAllDTO();
        assertNotNull(orderDTOs);
        assertTrue(orderDTOs.size() > 0);
    }

    @Test
    public void findOrderById() {
        Integer orderId = 1;
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder = orderDao.add(testOrder);
        Order findOrder = orderDao.findOrderById(orderId).get();
        assertNotNull(findOrder);
        assertTrue(findOrder.getOrderId().equals(orderId));
        assertEquals(EMPLOYEE_ID, findOrder.getOrderEmployeeId());
    }

    @Test
    public void findOrdersDTOByDateTime() {
        LocalDateTime startDate = LocalDateTime.of(2019,8,15,9,00,01);
        LocalDateTime endDate = LocalDateTime.of(2019,8,15,10,00,59);
        List orders = orderDao.findOrdersDTOByDateTime(startDate, endDate);
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

}