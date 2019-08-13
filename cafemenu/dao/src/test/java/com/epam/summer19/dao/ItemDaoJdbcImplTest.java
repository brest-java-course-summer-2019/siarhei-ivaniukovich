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

    @Autowired
    ItemDao itemDao;

    @Test
    public void add() {
        Item testItem = new Item();
        testItem.setItemId(1);
        testItem.setItemName("Name");
        testItem.setItemPrice(new BigDecimal(5.0));
        Item newItem = itemDao.add(testItem);
        assertEquals(new Integer(1),newItem.getItemId());
        assertEquals("Name",newItem.getItemName());
        assertEquals(new BigDecimal(5.0),newItem.getItemPrice());
    }

    @Test
    public void update() {
        Item testItem = new Item();
        testItem.setItemId(1);
        testItem.setItemName("Name");
        testItem.setItemPrice(new BigDecimal(5.0));
        Item newItem = itemDao.add(testItem);
        newItem.setItemId(2);
        newItem.setItemName("Name2");
        newItem.setItemPrice(new BigDecimal(6.5));
        itemDao.update(newItem);
        Item updatedItem = itemDao.findItemById(newItem.getItemId()).get();
        assertTrue(newItem.getItemId().equals(updatedItem.getItemId()));
        assertTrue(newItem.getItemName().equals(updatedItem.getItemName()));
        assertTrue(newItem.getItemPrice().equals(updatedItem.getItemPrice()));
    }

    @Test
    public void delete() {
        Item testItem = new Item();
        testItem.setItemId(1);
        testItem.setItemName("Name");
        testItem.setItemPrice(new BigDecimal(5.0));
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
        Integer itemId = 1;
        Item testItem = new Item();
        testItem.setItemId(itemId);
        testItem.setItemName("Name");
        testItem.setItemPrice(new BigDecimal(5.0));
        testItem = itemDao.add(testItem);
        Item findItem = itemDao.findItemById(itemId).get();
        assertNotNull(findItem);
        assertTrue(findItem.getItemId().equals(itemId));
        assertEquals(testItem.getItemId(), findItem.getItemId());
        assertEquals(testItem.getItemName(), findItem.getItemName());
        assertEquals(testItem.getItemPrice(), findItem.getItemPrice());
    }
}