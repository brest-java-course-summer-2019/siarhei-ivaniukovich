package com.epam.summer19.dao;

import com.epam.summer19.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class ItemDaoJdbcImplTest {

    private static final String WRAP = "Wrap";
    private static final BigDecimal PRICE = new BigDecimal(5.0);
    //private static final String PRICE = "Burger";

    @Autowired
    ItemDao itemDao;

    @Test
    public void add() {
        Item testItem = new Item();
        testItem.setItemName(WRAP);
        testItem.setItemPrice(PRICE);
        Item newItem = itemDao.add(testItem);
        assertNotNull(newItem.getItemId());
        assertEquals(WRAP,newItem.getItemName());
        assertEquals(PRICE,newItem.getItemPrice());
    }

    @Test
    public void update() {
        Item testItem = new Item();
        testItem.setItemName(WRAP);
        testItem.setItemPrice(PRICE);
        testItem = itemDao.add(testItem);
        testItem.setItemName("Frie");
        testItem.setItemPrice(new BigDecimal(6.5));
        itemDao.update(testItem);
        Item updatedItem = itemDao.findItemById(testItem.getItemId()).get();
        assertTrue(testItem.getItemId().equals(updatedItem.getItemId()));
        assertTrue(testItem.getItemName().equals(updatedItem.getItemName()));
        assertTrue(testItem.getItemPrice().equals(updatedItem.getItemPrice()));
    }

    @Test
    public void delete() {
        Item testItem = new Item();
        testItem.setItemName(WRAP);
        testItem.setItemPrice(PRICE);
        testItem = itemDao.add(testItem);
        List<Item> items = itemDao.findAll();
        int sizeBefore = items.size();
        itemDao.delete(testItem.getItemId());
        assertTrue((sizeBefore - 1) == itemDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<Item> items = itemDao.findAll();
        assertNotNull(items);
        assertTrue(items.size() > 0);
    }

    @Test
    public void findItemById() {
        Item testItem = itemDao.findItemById(1).get();
        assertNotNull(testItem);
        assertTrue(testItem.getItemId().equals(1));
        assertEquals(WRAP,testItem.getItemName());
    }
}