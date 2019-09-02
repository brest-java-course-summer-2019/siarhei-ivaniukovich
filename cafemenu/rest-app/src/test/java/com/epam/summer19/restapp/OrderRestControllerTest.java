package com.epam.summer19.restapp;

import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class OrderRestControllerTest {

    @Autowired
    private OrderRestController orderController;

    @Autowired
    private OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void afterReset() {
        /** Mockito.verifyNoMoreInteractions(orderService); **/
        Mockito.reset(orderService);
    }


    @Test
    public void testOrderAdd() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(createOrder(1,2)))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(orderService, Mockito.times(1)).add(any(Order.class));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = createOrder(2,4);
        String json = new ObjectMapper().writeValueAsString(order);

        mockMvc.perform(put("/orders/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());

        Mockito.verify(orderService, Mockito.times(1)).update(any());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/orders/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(orderService, Mockito.times(1)).delete(any());
    }


    @Test
    public void testOrderFindAll() throws Exception {
        Mockito.when(orderService.findAll()).thenReturn(Arrays.asList(createOrder(1,2), createOrder(2,3)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderEmployeeId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderEmployeeId", Matchers.is(3)))
        ;
        Mockito.verify(orderService).findAll();
    }

    @Test
    public void testOrderFindById() throws Exception {
        Mockito.when(orderService.findOrderById(1)).thenReturn(createOrder(1,5));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderEmployeeId", Matchers.is(5)))
        ;
        Mockito.verify(orderService, Mockito.times(1)).findOrderById(1);
    }

    private Order createOrder(int orderId, int orderEmployeeId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderEmployeeId(orderEmployeeId);
        return order;
    }

}