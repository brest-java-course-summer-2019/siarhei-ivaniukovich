package com.epam.summer19.web_app.consumers;
/**
import com.epam.summer19.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<Item> result = itemRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final List<Item> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<Item> result = itemRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
        // Setup
        final Item items = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        itemRestConsumerUnderTest.add(items);

        // Verify the results
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Item items = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        itemRestConsumerUnderTest.add(items);

        // Verify the results
    }

    @Test
    public void testAdd1() throws Exception {
        // Setup
        final Item item = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        itemRestConsumerUnderTest.add(item);

        // Verify the results
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException1() throws Exception {
        // Setup
        final Item item = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        itemRestConsumerUnderTest.add(item);

        // Verify the results
    }

    @Test
    public void testUpdate() throws Exception {
        // Setup
        final Item item = null;

        // Run the test
        itemRestConsumerUnderTest.update(item);

        // Verify the results
        verify(mockRestTemplate).put("url", null, null);
    }

    @Test
    public void testUpdate_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Item item = null;
        doThrow(RestClientException.class).when(mockRestTemplate).put("url", null, null);

        // Run the test
        itemRestConsumerUnderTest.update(item);

        // Verify the results
    }

    @Test
    public void testDelete() throws Exception {
        // Setup
        final Integer itemId = 0;

        // Run the test
        itemRestConsumerUnderTest.delete(itemId);

        // Verify the results
        verify(mockRestTemplate).delete("url", null);
    }

    @Test
    public void testDelete_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer itemId = 0;
        doThrow(RestClientException.class).when(mockRestTemplate).delete("url", null);

        // Run the test
        itemRestConsumerUnderTest.delete(itemId);

        // Verify the results
    }

    @Test
    public void testFindItemById() throws Exception {
        // Setup
        final Integer itemId = 0;
        final Item expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemById(itemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemById_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer itemId = 0;
        final Item expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemById(itemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemByName() throws Exception {
        // Setup
        final String itemName = "itemName";
        final Item expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemByName(itemName);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemByName_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final String itemName = "itemName";
        final Item expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final Item result = itemRestConsumerUnderTest.findItemByName(itemName);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
**/