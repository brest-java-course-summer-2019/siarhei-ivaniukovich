package com.epam.summer19.service;

import com.epam.summer19.dao.ItemDao;
import com.epam.summer19.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        final Item item = createItem(2);
        when(mockItemdao.add(any(Item.class))).thenReturn(item);
        
        itemServiceImplUnderTest.add(item);

        verify(mockItemdao).add(item);
    }

    @Test
    public void testUpdate() {
        final Item item = createItem(5);
        item.setItemName("Another");
        
        itemServiceImplUnderTest.update(item);

        verify(mockItemdao).update(item);
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
        expectedResult.setItemName("MockItemName");
        when(mockItemdao.findItemById(0)).thenReturn(Optional.of(expectedResult));
        
        final Item result = itemServiceImplUnderTest.findItemById(itemId);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemByName() {
        final String itemName = "Nuggets";
        final Item expectedResult = new Item();
        expectedResult.setItemName("Nuggets");
        when(mockItemdao.findItemByName("Nuggets")).thenReturn(Optional.of(expectedResult));

        final Item result = itemServiceImplUnderTest.findItemByName(itemName);

        assertEquals(expectedResult, result);
    }

    private static Item createItem(Integer itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName("Item"+itemId);
        item.setItemPrice(new BigDecimal(itemId+0.5));
        return item;
    }
    
}
