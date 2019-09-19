package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.model.ItemInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemInOrderServiceImplMockTest {

    @Mock
    private ItemInOrderDao mockIiodao;

    @InjectMocks
    private ItemInOrderServiceImpl itemInOrderServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemInOrderServiceImplUnderTest = new ItemInOrderServiceImpl(mockIiodao);
    }

    @Test
    public void testAddSingle() {
        itemInOrderServiceImplUnderTest.add(createIio(1,2,"Item1"));
        verify(mockIiodao).add(any());
    }

    @Test
    public void testUpdate() {
        itemInOrderServiceImplUnderTest.update(createIio(1,2,"Item3"));
        verify(mockIiodao).update(any());
    }

    @Test
    public void testDelete() {
        final Integer iioOrderId = 1;
        final Integer iioItemId = 2;
        itemInOrderServiceImplUnderTest.delete(iioOrderId, iioItemId);
        verify(mockIiodao).delete(1, 2);
    }

    @Test
    public void testFindAll() {
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockIiodao.findAll()).thenReturn(Arrays.asList());

        final List<ItemInOrder> result = itemInOrderServiceImplUnderTest.findAll();
        
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderId() {
        final Integer iioOrderId = 0;
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockIiodao.findIioByOrderId(0)).thenReturn(Arrays.asList());

        final List<ItemInOrder> result = itemInOrderServiceImplUnderTest.findIioByOrderId(iioOrderId);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderItemId() {
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;
        final ItemInOrder expectedResult = new ItemInOrder();
        expectedResult.setIioItemName("MockItemInOrderName");
        when(mockIiodao.findIioByOrderItemId(0, 0)).thenReturn(Optional.of(expectedResult));

        final ItemInOrder result = itemInOrderServiceImplUnderTest.findIioByOrderItemId(iioOrderId, iioItemId);

        assertEquals(expectedResult, result);
    }

    private static ItemInOrder createIio(Integer orderId, Integer itemId, String itemName) {
        ItemInOrder iteminorder = new ItemInOrder();
        iteminorder.setIioOrderId(orderId);
        iteminorder.setIioItemId(itemId);
        iteminorder.setIioItemName(itemName);
        iteminorder.setIioItemPrice(new BigDecimal("1.0"));
        iteminorder.setIioItemCount(1);

        return iteminorder;
    }
}
