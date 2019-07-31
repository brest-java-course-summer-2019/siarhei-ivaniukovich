package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
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
    com.epam.summer19.dao.OrderDao orderDao;

    @Test
    public void findAll() {
;
    }

    @Test
    public void addOrders() {

    }

}