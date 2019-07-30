package com.epam.summer19.service;

import com.epam.summer19.model.OrdersList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class OrdersListServiceImplTest {

    @Autowired
    private OrdersListService ordersListService;

    @Test
    void findAll() {
        List<OrdersList> ordersLists = ordersListService.findAll();
        assertNotNull(ordersLists);
        assertFalse(ordersLists.isEmpty());
    }

    @Test
    void findByOrderId() {
        Integer orderId = 1;
        OrdersList ordersList = ordersListService.findByOrderId(orderId);
        assertNotNull(ordersList);
        assertEquals("1", ordersList.getOrderId());
    }

    @Test
    void update() {
        Integer orderId =11;
        OrdersList ordersList = createOrdersList();
        ordersList.setOrderId(orderId);
        ordersListService.update(ordersList);
        ordersList = ordersListService.findByOrderId(orderId);

        assertNotNull(ordersList);
        assertEquals("11", ordersList.getOrderId());
    }

    @Test()
    void delete() {
        int id = 3;
        ordersListService.delete(id);
        Assertions.assertThrows(RuntimeException.class, () -> ordersListService.findById(id));
    }

    private OrdersList createOrdersList() {
        OrdersList ordersList = new OrdersList();
        ordersList.setOrderId("11");
        return ordersList;
    }
}