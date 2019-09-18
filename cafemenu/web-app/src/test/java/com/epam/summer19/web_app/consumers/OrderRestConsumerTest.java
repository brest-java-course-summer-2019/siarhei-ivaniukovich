package com.epam.summer19.web_app.consumers;
/**
import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private OrderRestConsumer orderRestConsumerUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        orderRestConsumerUnderTest = new OrderRestConsumer("url", mockRestTemplate);
    }

    @Test
    public void testFindAll() throws Exception {
        // Setup
        final List<Order> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<Order> result = orderRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final List<Order> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<Order> result = orderRestConsumerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAllDTO() throws Exception {
        // Setup
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<OrderDTO> result = orderRestConsumerUnderTest.findAllDTO();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAllDTO_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<OrderDTO> result = orderRestConsumerUnderTest.findAllDTO();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
        // Setup
        final Order orders = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        orderRestConsumerUnderTest.add(orders);

        // Verify the results
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Order orders = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        orderRestConsumerUnderTest.add(orders);

        // Verify the results
    }

    @Test
    public void testAdd1() throws Exception {
        // Setup
        final Order order = null;
        final Order expectedResult = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenReturn(null);

        // Run the test
        final Order result = orderRestConsumerUnderTest.add(order);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd_RestTemplateThrowsRestClientException1() throws Exception {
        // Setup
        final Order order = null;
        final Order expectedResult = null;
        when(mockRestTemplate.postForEntity("url", null, null, null)).thenThrow(RestClientException.class);

        // Run the test
        final Order result = orderRestConsumerUnderTest.add(order);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdate() throws Exception {
        // Setup
        final Order order = null;

        // Run the test
        orderRestConsumerUnderTest.update(order);

        // Verify the results
        verify(mockRestTemplate).put("url", null, null);
    }

    @Test
    public void testUpdate_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Order order = null;
        doThrow(RestClientException.class).when(mockRestTemplate).put("url", null, null);

        // Run the test
        orderRestConsumerUnderTest.update(order);

        // Verify the results
    }

    @Test
    public void testDelete() throws Exception {
        // Setup
        final Integer orderId = 0;

        // Run the test
        orderRestConsumerUnderTest.delete(orderId);

        // Verify the results
        verify(mockRestTemplate).delete("url", null);
    }

    @Test
    public void testDelete_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer orderId = 0;
        doThrow(RestClientException.class).when(mockRestTemplate).delete("url", null);

        // Run the test
        orderRestConsumerUnderTest.delete(orderId);

        // Verify the results
    }

    @Test
    public void testFindOrderById() throws Exception {
        // Setup
        final Integer orderId = 0;
        final Order expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final Order result = orderRestConsumerUnderTest.findOrderById(orderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrderById_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final Integer orderId = 0;
        final Order expectedResult = null;
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final Order result = orderRestConsumerUnderTest.findOrderById(orderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrdersDTOByDateTime() throws Exception {
        // Setup
        final LocalDateTime startDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        final LocalDateTime endDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenReturn(null);

        // Run the test
        final List<OrderDTO> result = orderRestConsumerUnderTest.findOrdersDTOByDateTime(startDateTime, endDateTime);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrdersDTOByDateTime_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        final LocalDateTime startDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        final LocalDateTime endDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", null, null)).thenThrow(RestClientException.class);

        // Run the test
        final List<OrderDTO> result = orderRestConsumerUnderTest.findOrdersDTOByDateTime(startDateTime, endDateTime);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
**/