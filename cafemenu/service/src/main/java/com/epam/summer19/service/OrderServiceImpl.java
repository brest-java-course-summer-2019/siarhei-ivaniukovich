package com.epam.summer19.service;

import com.epam.summer19.dao.OrderDao;
import com.epam.summer19.model.Order;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;
    
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void add(Order... orders) {
        for(Order order : orders) {
            orderDao.add(order);
        }
    }

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }


    @Override
    public void update(Order order) {
        LOGGER.debug("Order update({})", order);
        orderDao.update(order);
    }

    @Override
    public void delete(Integer orderId) {
        LOGGER.debug("Order delete({})", orderId);
        orderDao.delete(orderId);
    }

    @Override
    public List<Order> findAll() {
        LOGGER.debug("Find all Orders");
        return orderDao.findAll();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Find order by orderId");
        return orderDao.findOrderById(orderId)
                .orElseThrow(() ->  new RuntimeException("Failed to get orders from DB"));
    }

    @Override
    public List<Order> findOrdersByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LOGGER.debug("Find orders between dates {} and {}", startDateTime, endDateTime);
        return orderDao.findOrdersByDateTime(startDateTime, endDateTime);
    }

}
