package com.epam.summer19.web_app.consumers;

import com.epam.summer19.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ItemRestConsumer itemRestConsumerUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemRestConsumerUnderTest = new ItemRestConsumer("url", mockRestTemplate);
    }

    @Test
    public void testFindAll() throws Exception {
        // Setup
        final List<Item> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(),HttpStatus.OK));

        // Run the test
        final List<Item> result = itemRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
        // Setup
        final Item item = createItem(2,2);
        when(mockRestTemplate.postForEntity("url", createItem(2,2), Item.class))
                .thenReturn(new ResponseEntity<>(createItem(1,1), HttpStatus.OK));

        // Run the test
        itemRestConsumerUnderTest.add(item);

        // Verify the results
    }


    @Test
    public void testUpdate() throws Exception {
        // Setup
        final Item item = createItem(3,4);

        // Run the test
        itemRestConsumerUnderTest.update(item);

        // Verify the results
        verify(mockRestTemplate).put("url", item);
    }

    @Test
    public void testDelete() throws Exception {
        // Setup
        final Integer itemId = 0;

        // Run the test
        itemRestConsumerUnderTest.delete(itemId);

        // Verify the results
        verify(mockRestTemplate).delete("url/0");
    }


    @Test
    public void testFindItemById() throws Exception {
        // Setup
        final Integer itemId = 1;
        final Item expectedResult = createItem(1,1);
        when(mockRestTemplate.getForEntity("url/"+itemId, Item.class))
                .thenReturn(new ResponseEntity<>(createItem(1,1), HttpStatus.OK));

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemById(itemId);

        // Verify the results
        assertEquals(expectedResult.getItemName(), result.getItemName());
    }

    @Test
    public void testFindItemByName() throws Exception {
        // Setup
        final String itemName = "It1";
        final Item expectedResult = createItem(1,1);
        when(mockRestTemplate.postForEntity("url/byname", itemName, Item.class))
                .thenReturn(new ResponseEntity<>(createItem(1,1), HttpStatus.OK));

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemByName(itemName);

        // Verify the results
        assertEquals(expectedResult.getItemName(), result.getItemName());
    }

    private Item createItem(int itemId, int namePostfix) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName("It" + namePostfix);
        item.setItemPrice(new BigDecimal("2.4"));
        return item;
    }
}
