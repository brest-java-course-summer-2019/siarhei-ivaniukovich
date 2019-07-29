package com.epam.summer19.service;

import com.epam.summer19.dao.OrdersListDao;
import com.epam.summer19.model.OrdersList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrdersListServiceImpl implements OrdersListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersListServiceImpl.class);

    private OrdersListDao orderslistdao;

    public OrdersListServiceImpl(OrdersListDao orderslistdao) {
        this.orderslistdao = orderslistdao;
    }

    @Override
    public OrdersList add(OrdersList orderslist) {
        LOGGER.debug("add({})", orderslist);
        return orderslistdao.add(orderslist);
    }

    @Override
    public void update(OrdersList orderslist) {
        LOGGER.debug("update({})", orderslist);
        orderslistdao.update(orderslist);
    }

    @Override
    public void delete(Integer orderId) {
        LOGGER.debug("delete({})", orderId);
        orderslistdao.delete(orderId);
    }

    @Override
    public List<OrdersListDao> findAll() {
        LOGGER.debug("Find all Orders Lists");
        return orderslistdao.findAll();
    }

    @Override
    public OrdersList findByOrderId(Integer orderId) {
        LOGGER.debug("findByOrderId({})", orderId);
        return orderslistdao.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Failed to get orderslist from DB"));
    }

    @Override
    public OrdersList findByEmployeeId(Integer employeeId) {
        LOGGER.debug("findByEmployeeId({})", employeeId);
        return orderslistdao.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Failed to get orderslist from DB by employeeId"));
    }

}
