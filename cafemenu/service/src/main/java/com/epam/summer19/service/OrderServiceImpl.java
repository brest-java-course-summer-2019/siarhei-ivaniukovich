package com.epam.summer19.service;

import com.epam.summer19.dao.OrderDao;
import com.epam.summer19.dto.OrderDTO;
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
    public Order add(Order order) {
        LOGGER.debug("OrderServiceImpl: update({})", order);
        return orderDao.add(order);
    }

    @Override
    public void update(Order order) {
        LOGGER.debug("OrderServiceImpl: update({})", order);
        orderDao.update(order);
    }

    @Override
    public void delete(Integer orderId) {
        LOGGER.debug("OrderServiceImpl: delete({})", orderId);
        orderDao.delete(orderId);
    }

    @Override
    public List<Order> findAll() {
        LOGGER.debug("OrderServiceImpl: findAll()");
        return orderDao.findAll();
    }

    @Override
    public List<OrderDTO> findAllDTO() {
        LOGGER.debug("OrderServiceImpl: findAllDTO()");
        return orderDao.findAllDTO();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("OrderServiceImpl: findOrderById({})", orderId);
        return orderDao.findOrderById(orderId)
                .orElseThrow(() ->  new RuntimeException("Failed to get orders from DB"));
    }

    @Override
    public List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LOGGER.debug("OrderServiceImpl: findOrdersDTOByDateTime({}, {})", startDateTime, endDateTime);
        return orderDao.findOrdersDTOByDateTime(startDateTime, endDateTime);
    }

}
