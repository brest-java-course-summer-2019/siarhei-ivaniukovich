package com.epam.summer19.service;

import com.epam.summer19.dao.OrderDao;
import com.epam.summer19.model.Order;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDao orderdao;

    public OrderServiceImpl(OrderDao orderdao) {
        this.orderdao = orderdao;
    }

    @Override
    public List<OrderDao> findAll() {
        LOGGER.debug("Find all Orders");
        return orderdao.findAll();
    }

    @Override
    public void update(Order order) {
        LOGGER.debug("update({})", order);
        orderdao.update(order);
    }

    @Override
    public void delete(Integer orderId) {
        LOGGER.debug("delete({})", orderId);
        orderdao.delete(orderId);
    }

}
