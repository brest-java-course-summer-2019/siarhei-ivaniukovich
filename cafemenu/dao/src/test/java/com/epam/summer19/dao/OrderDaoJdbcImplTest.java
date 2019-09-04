package com.epam.summer19.dao;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private static final Integer ORDER_STATUS = 1;
   // private static final LocalDateTime ORDER_TIME = LocalDateTime.parse("2019-08-21T09:22:14");

    @Autowired
    OrderDao orderDao;

    @Autowired
    ItemInOrderDao itemInOrderDao;

    @Test
    public void add() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder.setOrderStatus(ORDER_STATUS);
        Order newOrder = orderDao.add(testOrder);
        assertNotNull(newOrder.getOrderId());
        assertEquals(new Integer(EMPLOYEE_ID),newOrder.getOrderEmployeeId());
        assertNotNull(newOrder.getOrderDateTime());
        assertEquals(new Integer(ORDER_STATUS),newOrder.getOrderStatus());
    }

    @Test
    public void update() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder.setOrderStatus(ORDER_STATUS);
        testOrder = orderDao.add(testOrder);
        testOrder.setOrderEmployeeId(22);
        testOrder.setOrderStatus(2);
        orderDao.update(testOrder);
        Order updatedOrder = orderDao.findOrderById(testOrder.getOrderId()).get();
        assertTrue(testOrder.getOrderId().equals(updatedOrder.getOrderId()));
        assertTrue(testOrder.getOrderEmployeeId().equals(updatedOrder.getOrderEmployeeId()));
        assertTrue(testOrder.getOrderStatus().equals(updatedOrder.getOrderStatus()));
    }

    @Test
    public void delete() {
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder.setOrderStatus(ORDER_STATUS);
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
    public void findOrderById() {
        Integer orderId = 1;
        Order testOrder = new Order();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder.setOrderStatus(ORDER_STATUS);
        testOrder = orderDao.add(testOrder);
        Order findOrder = orderDao.findOrderById(orderId).get();
        assertNotNull(findOrder);
        assertTrue(findOrder.getOrderId().equals(orderId));
        assertEquals(EMPLOYEE_ID, findOrder.getOrderEmployeeId());
        assertEquals(ORDER_STATUS, findOrder.getOrderStatus());
    }

    @Test
    public void findOrdersByDateTime() {
        LocalDateTime startDate = LocalDateTime.of(2019,8,15,9,00,01);
        LocalDateTime endDate = LocalDateTime.of(2019,8,15,10,00,59);
        List orders = orderDao.findOrdersByDateTime(startDate, endDate);
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

    @Test
    public void calcSummaryOrderPrice() {
        Order testOrder = new Order();
        ItemInOrder iio = new ItemInOrder();
        testOrder.setOrderEmployeeId(EMPLOYEE_ID);
        testOrder.setOrderStatus(ORDER_STATUS);
        testOrder = orderDao.add(testOrder);
        iio.setIioOrderId(testOrder.getOrderId());
        iio.setIioItemId(2);
        iio.setIioItemName("Item");
        iio.setIioItemPrice(new BigDecimal("1.0"));
        iio.setIioItemCount(2);
        itemInOrderDao.add(iio);
        orderDao.calcSummaryOrderPrice(testOrder.getOrderId());
        Order resultOrder = orderDao.findOrderById(testOrder.getOrderId()).get();

        //assertNotNull(resultOrder.getSummaryPrice());
        //assertTrue(resultOrder.getSummaryPrice().equals(2.0));
    }
}