package com.epam.summer19.service;

import com.epam.summer19.dao.ItemDao;
import com.epam.summer19.model.Item;
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

public class ItemServiceImplTest {

    @Mock
    private ItemDao mockItemdao;

    private ItemServiceImpl itemServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemServiceImplUnderTest = new ItemServiceImpl(mockItemdao);
    }

    @Test
    public void testAdd() {
        // Setup
        final Item items = null;
        when(mockItemdao.add(null)).thenReturn(null);

        // Run the test
        itemServiceImplUnderTest.add(items);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final Item item = null;

        // Run the test
        itemServiceImplUnderTest.update(item);

        // Verify the results
        verify(mockItemdao).update(null);
    }

    @Test
    public void testDelete() {
        // Setup
        final Integer itemId = 0;

        // Run the test
        itemServiceImplUnderTest.delete(itemId);

        // Verify the results
        verify(mockItemdao).delete(0);
    }

    @Test
    public void testFindAll() {
        // Setup
        final List<Item> expectedResult = Arrays.asList();
        when(mockItemdao.findAll()).thenReturn(Arrays.asList());

        // Run the test
        final List<Item> result = itemServiceImplUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    /*@Test
    public void testFindItemById() {
        // Setup
        final Integer itemId = 0;
        final Item expectedResult = null;
        when(mockItemdao.findItemById(0)).thenReturn(Optional.empty());

        // Run the test
        final Item result = itemServiceImplUnderTest.findItemById(itemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }*/
}
