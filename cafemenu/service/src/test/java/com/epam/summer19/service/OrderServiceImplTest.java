package com.epam.summer19.service;

import com.epam.summer19.dao.OrderDao;
import com.epam.summer19.model.Order;
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

public class OrderServiceImplTest {

    @Mock
    private OrderDao mockOrderdao;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        orderServiceImplUnderTest = new OrderServiceImpl(mockOrderdao);
    }

    @Test
    public void testAdd() {
        // Setup
        final Order orders = null;
        when(mockOrderdao.add(null)).thenReturn(null);

        // Run the test
        orderServiceImplUnderTest.add(orders);

        // Verify the results
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
        final Order expectedResult = null;
        when(mockOrderdao.findOrderById(1)).thenReturn(Optional.empty());

        // Run the test
        final Order result = orderServiceImplUnderTest.findOrderById(orderId);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
