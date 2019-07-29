package com.epam.summer19;

import com.epam.summer19.model.Orders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class OrderDaoJdbcImplTest {

    @Autowired
    com.epam.summer19.OrderDao orderDao;

    @Test
    public void findAll() {
        List<Orders> orderss = ordersDao.findAll();
        Assert.assertNotNull(orderss);
        Assert.assertTrue(orderss.size() > 0);
    }

    @Test
    public void addOrders() {
        Orders testOrders = new Orders();
        testOrders.setOrdersName("QA");
        Orders newOrders = ordersDao.add(testOrders);
        Assert.assertNotNull(newOrders.getOrdersId());
    }

}