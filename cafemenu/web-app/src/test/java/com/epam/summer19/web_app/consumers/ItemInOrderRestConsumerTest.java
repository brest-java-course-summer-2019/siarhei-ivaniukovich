package com.epam.summer19.web_app.consumers;
/**
import com.epam.summer19.model.ItemInOrder;
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

public class ItemInOrderRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ItemInOrderRestConsumer itemInOrderRestConsumerUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemInOrderRestConsumerUnderTest = new ItemInOrderRestConsumer("url", mockRestTemplate);
    }

    @Test
    public void testFindAll() throws Exception {
        // Setup
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
        // Setup
        final ItemInOrder iios = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        itemInOrderRestConsumerUnderTest.add(iios);

        // Verify the results
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final ItemInOrder iios = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        itemInOrderRestConsumerUnderTest.add(iios);

        // Verify the results
    }

    @Test
    public void testAdd1() throws Exception {
        // Setup
        final ItemInOrder iio = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        itemInOrderRestConsumerUnderTest.add(iio);

        // Verify the results
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException1() throws Exception {
        // Setup
        final ItemInOrder iio = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        itemInOrderRestConsumerUnderTest.add(iio);

        // Verify the results
    }

    @Test
    public void testUpdate() throws Exception {
        // Setup
        final ItemInOrder iio = null;

        // Run the test
        itemInOrderRestConsumerUnderTest.update(iio);

        // Verify the results
        verify(mockRestTemplate).put("url", null, null);
    }

    @Test
    public void testUpdate_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final ItemInOrder iio = null;
        doThrow(RestClientException.class).when(mockRestTemplate).put("url", null, null);

        // Run the test
        itemInOrderRestConsumerUnderTest.update(iio);

        // Verify the results
    }

    @Test
    public void testDelete() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;

        // Run the test
        itemInOrderRestConsumerUnderTest.delete(iioOrderId, iioItemId);

        // Verify the results
        verify(mockRestTemplate).delete("url", null);
    }

    @Test
    public void testDelete_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;
        doThrow(RestClientException.class).when(mockRestTemplate).delete("url", null);

        // Run the test
        itemInOrderRestConsumerUnderTest.delete(iioOrderId, iioItemId);

        // Verify the results
    }

    @Test
    public void testFindIioByOrderId() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findIioByOrderId(iioOrderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderId_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findIioByOrderId(iioOrderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderItemId() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;
        final ItemInOrder expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final ItemInOrder result = itemInOrderRestConsumerUnderTest.findIioByOrderItemId(iioOrderId, iioItemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindIioByOrderItemId_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer iioOrderId = 0;
        final Integer iioItemId = 0;
        final ItemInOrder expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final ItemInOrder result = itemInOrderRestConsumerUnderTest.findIioByOrderItemId(iioOrderId, iioItemId);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
**/