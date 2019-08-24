package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.model.ItemInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        final ItemInOrder iteminorder = null;
        when(mockIiodao.add(null)).thenReturn(null);
        
        itemInOrderServiceImplUnderTest.add(iteminorder);

        verify(mockIiodao).add(iteminorder);
    }

    @Test
    public void testAddMultiple() {
        final ItemInOrder iioone = null;
        when(mockIiodao.add(null)).thenReturn(null);

        itemInOrderServiceImplUnderTest.add(iioone);

        verify(mockIiodao).add(iioone);
    }

    @Test
    public void testUpdate() {
        final ItemInOrder iteminorder = null;

        itemInOrderServiceImplUnderTest.update(iteminorder);

        verify(mockIiodao).update(null);
    }

    @Test
    public void testDelete() {
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;

        itemInOrderServiceImplUnderTest.delete(iioOrderId, iioItemId);

        verify(mockIiodao).delete(0, 0);
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

}
