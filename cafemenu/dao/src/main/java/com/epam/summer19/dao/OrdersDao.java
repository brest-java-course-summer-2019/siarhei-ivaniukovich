package com.epam.summer19;

import com.epam.summer19.model.Orders;
import java.util.List;

public class OrdersDao {

    Orders add(Orders orders);

    void update(Orders orders);

    void delete(Integer orderId);

    List<Orders> findAll();
}
