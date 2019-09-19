package com.epam.summer19.dao;

import com.epam.summer19.model.ItemInOrder;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class ItemInOrderDaoJdbcImplTest {

    private static final Integer IIO_ORDER_ID = 1;
    private static final Integer IIO_ITEM_ID = 2;
    private static final String IIO_ITEM_NAME = "Wrap";
    private static final BigDecimal IIO_ITEM_PRICE = new BigDecimal("3.0");
    private static final Integer IIO_ITEM_COUNT = 1;

    @Autowired
    ItemInOrderDao itemInOrderDao;

    @Test
    public void add() {
        ItemInOrder testIio = new ItemInOrder();
        testIio.setIioOrderId(IIO_ORDER_ID);
        testIio.setIioItemId(IIO_ITEM_ID);
        testIio.setIioItemName(IIO_ITEM_NAME);
        testIio.setIioItemPrice(IIO_ITEM_PRICE);
        testIio.setIioItemCount(IIO_ITEM_COUNT);
        ItemInOrder newIio = itemInOrderDao.add(testIio);
        assertEquals(IIO_ORDER_ID,newIio.getIioOrderId());
        assertEquals(IIO_ITEM_ID,newIio.getIioItemId());
        assertEquals(IIO_ITEM_NAME,newIio.getIioItemName());
        assertEquals(IIO_ITEM_PRICE,newIio.getIioItemPrice());
        assertEquals(IIO_ITEM_COUNT,newIio.getIioItemCount());
    }

    @Test
    public void update() {
        ItemInOrder testIio = new ItemInOrder();
        testIio.setIioOrderId(IIO_ORDER_ID);
        testIio.setIioItemId(IIO_ITEM_ID);
        testIio.setIioItemName(IIO_ITEM_NAME);
        testIio.setIioItemPrice(IIO_ITEM_PRICE);
        testIio.setIioItemCount(IIO_ITEM_COUNT);
        testIio = itemInOrderDao.add(testIio);
        testIio.setIioOrderId(new Integer(2));
        testIio.setIioItemId(new Integer(3));
        testIio.setIioItemName("AnotherWrap");
        testIio.setIioItemPrice(new BigDecimal("4.0"));
        testIio.setIioItemCount(new Integer(2));
        itemInOrderDao.update(testIio);
        ItemInOrder updatedIio = itemInOrderDao.findIioByOrderItemId(2, 3).get();
        assertTrue(testIio.getIioOrderId().equals(updatedIio.getIioOrderId()));
        assertTrue(testIio.getIioItemId().equals(updatedIio.getIioItemId()));
        assertTrue(testIio.getIioItemName().equals(updatedIio.getIioItemName()));
        assertTrue(testIio.getIioItemPrice().equals(updatedIio.getIioItemPrice()));
        assertTrue(testIio.getIioItemCount().equals(updatedIio.getIioItemCount()));
    }

    @Test
    public void delete() {
        ItemInOrder testIio = new ItemInOrder();
        testIio.setIioOrderId(IIO_ORDER_ID);
        testIio.setIioItemId(IIO_ITEM_ID);
        testIio.setIioItemName(IIO_ITEM_NAME);
        testIio.setIioItemPrice(IIO_ITEM_PRICE);
        testIio.setIioItemCount(IIO_ITEM_COUNT);
        testIio = itemInOrderDao.add(testIio);
        List<ItemInOrder> iio = itemInOrderDao.findAll();
        int sizeBefore = iio.size();
        itemInOrderDao.delete(testIio.getIioOrderId(), testIio.getIioItemId());
        assertTrue((sizeBefore - 1) == itemInOrderDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<ItemInOrder> iio = itemInOrderDao.findAll();
        assertNotNull(iio);
        assertTrue(iio.size() > 0);
    }

    @Test
    public void findIioByOrderId() {
        ItemInOrder testIio = new ItemInOrder();
        testIio.setIioOrderId(IIO_ORDER_ID);
        testIio.setIioItemId(IIO_ITEM_ID);
        testIio.setIioItemName(IIO_ITEM_NAME);
        testIio.setIioItemPrice(IIO_ITEM_PRICE);
        testIio.setIioItemCount(IIO_ITEM_COUNT);
        testIio = itemInOrderDao.add(testIio);
        List<ItemInOrder> findIios = itemInOrderDao.findIioByOrderId(IIO_ORDER_ID);
        assertNotNull(findIios);
        assertTrue(findIios.size() > 0);
    }

    @Test
    public void findIioByOrderItemId() {
        ItemInOrder testIio = new ItemInOrder();
        testIio.setIioOrderId(IIO_ORDER_ID);
        testIio.setIioItemId(IIO_ITEM_ID);
        testIio.setIioItemName(IIO_ITEM_NAME);
        testIio.setIioItemPrice(IIO_ITEM_PRICE);
        testIio.setIioItemCount(IIO_ITEM_COUNT);
        itemInOrderDao.add(testIio);
        ItemInOrder findIio = itemInOrderDao.findIioByOrderItemId(IIO_ORDER_ID, IIO_ITEM_ID).get();
        assertNotNull(findIio);
        assertEquals(IIO_ORDER_ID, findIio.getIioOrderId());
        assertEquals(IIO_ITEM_ID, findIio.getIioItemId());
        assertEquals(IIO_ITEM_NAME, findIio.getIioItemName());
        assertEquals(IIO_ITEM_PRICE, findIio.getIioItemPrice());
        assertEquals(IIO_ITEM_COUNT, findIio.getIioItemCount());
    }
}
