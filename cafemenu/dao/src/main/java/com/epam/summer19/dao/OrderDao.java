package com.epam.summer19;

import com.epam.summer19.model.Order;
import java.util.List;

public class OrderDao {

    Order add(Order order);

    void update(Order order);

    void delete(Integer orderId);

    List<Order> findAll();
}
