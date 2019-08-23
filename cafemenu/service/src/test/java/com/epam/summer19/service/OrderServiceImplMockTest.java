package com.epam.summer19.service;

import com.epam.summer19.dao.OrderDao;
import com.epam.summer19.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceImplMockTest {

    @Mock
    private OrderDao mockOrderdao;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        orderServiceImplUnderTest = new OrderServiceImpl(mockOrderdao);
    }

    @Test
    public void testAddSingle() {
        // Setup
        final Order orders = null;
        when(mockOrderdao.add(null)).thenReturn(null);

        // Run the test
        orderServiceImplUnderTest.add(orders);

        // Verify the results
    }

    @Test
    public void testAddMultiple() {
        final Order orderone = null;
        final Order ordertwo = null;
        when(mockOrderdao.add(null)).thenReturn(null);

        orderServiceImplUnderTest.add(orderone, ordertwo);
    }

    @Test
    public void testUpdate() {
        // Setup
        final Order order = null;

        // Run the test
        orderServiceImplUnderTest.update(order);

        // Verify the results
        verify(mockOrderdao).update(null);
    }

    @Test
    public void testDelete() {
        // Setup
        final Integer orderId = 1;

        // Run the test
        orderServiceImplUnderTest.delete(orderId);

        // Verify the results
        verify(mockOrderdao).delete(1);
    }

    @Test
    public void testFindAll() {
        // Setup
        final List<Order> expectedResult = Arrays.asList();
        when(mockOrderdao.findAll()).thenReturn(Arrays.asList());

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrderById() {
        // Setup
        final Integer orderId = 1;
        final Order expectedResult = new Order();
        expectedResult.setOrderEmployeeId(22);
        when(mockOrderdao.findOrderById(1)).thenReturn(Optional.of(expectedResult));

        // Run the test
        final Order result = orderServiceImplUnderTest.findOrderById(orderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrdersByDateTime() {
        // Setup
        final LocalDateTime startDateTime = LocalDateTime.of(2019, 8, 21, 9, 0, 0);
        final LocalDateTime endDateTime = LocalDateTime.of(2019, 8, 21, 9, 0, 0);
        final List<Order> expectedResult = Arrays.asList();
        when(mockOrderdao.findOrdersByDateTime(LocalDateTime.of(2019, 8, 21, 9, 0, 0), LocalDateTime.of(2017, 1, 1, 0, 0, 0))).thenReturn(Arrays.asList());

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.findOrdersByDateTime(startDateTime, endDateTime);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
