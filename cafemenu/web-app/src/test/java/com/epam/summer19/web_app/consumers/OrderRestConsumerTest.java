package com.epam.summer19.web_app.consumers;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        final List<Order> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(),HttpStatus.OK));
        
        final List<Order> result = orderRestConsumerUnderTest.findAll();
        
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAllDTO() throws Exception {
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("urldto", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(),HttpStatus.OK));

        final List<OrderDTO> result = orderRestConsumerUnderTest.findAllDTO();

        assertEquals(expectedResult, result);
    }


    @Test
    public void testAdd() throws Exception {
        final Order order = createOrder(2);
        when(mockRestTemplate.postForEntity("url", order, Order.class))
                .thenReturn(new ResponseEntity<>(createOrder(2), HttpStatus.OK));

        orderRestConsumerUnderTest.add(order);
    }


    @Test
    public void testUpdate() throws Exception {
        final Order order = createOrder(3);

        orderRestConsumerUnderTest.update(order);
        
        verify(mockRestTemplate).put("url", order);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer orderId = 1;

        orderRestConsumerUnderTest.delete(orderId);
        
        verify(mockRestTemplate).delete("url/1");
    }


    @Test
    public void testFindOrderById() throws Exception {
        
        final Integer orderId = 1;
        final Order expectedResult = createOrder(orderId);
        when(mockRestTemplate.getForEntity("url/"+orderId, Order.class))
                .thenReturn(new ResponseEntity<>(createOrder(orderId), HttpStatus.OK));

        
        final Order result = orderRestConsumerUnderTest.findOrderById(orderId);

        
        assertEquals(expectedResult.getOrderId(), result.getOrderId());
    }

    @Test
    public void testFindOrdersDTOByDateTime() throws Exception {
        
        final Integer orderId = 1;
        final String startDateTime = "2019-04-20T08:09:00";
        final String endDateTime = "2019-09-21T08:09:00";
        final List<OrderDTO> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("urldto/"+startDateTime+"/"+endDateTime, List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        
        final List<OrderDTO> result = orderRestConsumerUnderTest
                .findOrdersDTOByDateTime(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));

        
        assertEquals(expectedResult, result);
    }

    private Order createOrder(int orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderEmployeeId(orderId+30);
        return order;
    }
}
