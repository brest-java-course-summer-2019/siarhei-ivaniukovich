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

public class ItemServiceImplMockTest {

    @Mock
    private ItemDao mockItemdao;

    private ItemServiceImpl itemServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemServiceImplUnderTest = new ItemServiceImpl(mockItemdao);
    }

    @Test
    public void testAddSingle() {
        final Item item = null;
        when(mockItemdao.add(null)).thenReturn(null);
        
        itemServiceImplUnderTest.add(item);

        verify(mockItemdao).add(item);
    }
    
    @Test
    public void testAddMultiple() {
        final Item itemone = null;
        when(mockItemdao.add(null)).thenReturn(null);
        
        itemServiceImplUnderTest.add(itemone);

        verify(mockItemdao).add(itemone);
    }

    @Test
    public void testUpdate() {
        final Item item = null;
        
        itemServiceImplUnderTest.update(item);

        verify(mockItemdao).update(null);
    }

    @Test
    public void testDelete() {
        final Integer itemId = 0;

        itemServiceImplUnderTest.delete(itemId);

        verify(mockItemdao).delete(0);
    }

    @Test
    public void testFindAll() {
        final List<Item> expectedResult = Arrays.asList();
        when(mockItemdao.findAll()).thenReturn(Arrays.asList());
        
        final List<Item> result = itemServiceImplUnderTest.findAll();
        
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemById() {
        final Integer itemId = 0;
        final Item expectedResult = new Item();
        expectedResult.setItemName("MockItemInOrderName");
        when(mockItemdao.findItemById(0)).thenReturn(Optional.of(expectedResult));
        
        final Item result = itemServiceImplUnderTest.findItemById(itemId);

        assertEquals(expectedResult, result);
    }
}
