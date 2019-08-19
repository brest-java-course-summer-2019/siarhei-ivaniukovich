package com.epam.summer19.service;

import com.epam.summer19.dao.ItemInOrderDao;
import com.epam.summer19.model.ItemInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemInOrderServiceImplTest {

    @Mock
    private ItemInOrderDao mockIiodao;

    private ItemInOrderServiceImpl itemInOrderServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemInOrderServiceImplUnderTest = new ItemInOrderServiceImpl(mockIiodao);
    }

    @Test
    public void testAdd() {
        // Setup
        final ItemInOrder iteminorders = null;
        when(mockIiodao.add(null)).thenReturn(null);

        // Run the test
        itemInOrderServiceImplUnderTest.add(iteminorders);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final ItemInOrder iteminorder = null;

        // Run the test
        itemInOrderServiceImplUnderTest.update(iteminorder);

        // Verify the results
        verify(mockIiodao).update(null);
    }

    @Test
    public void testDelete() {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;

        // Run the test
        itemInOrderServiceImplUnderTest.delete(iioOrderId, iioItemId);

        // Verify the results
        verify(mockIiodao).delete(0, 0);
    }

    @Test
    public void testFindAll() {
        // Setup
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockIiodao.findAll()).thenReturn(Arrays.asList());

        // Run the test
        final List<ItemInOrder> result = itemInOrderServiceImplUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderId() {
        // Setup
        final Integer iioOrderId = 0;
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockIiodao.findIioByOrderId(0)).thenReturn(Arrays.asList());

        // Run the test
        final List<ItemInOrder> result = itemInOrderServiceImplUnderTest.findIioByOrderId(iioOrderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderItemId() {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;
        final ItemInOrder expectedResult = null;
        when(mockIiodao.findIioByOrderItemId(0, 0)).thenReturn(Optional.empty());

        // Run the test
        final ItemInOrder result = itemInOrderServiceImplUnderTest.findIioByOrderItemId(iioOrderId, iioItemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
