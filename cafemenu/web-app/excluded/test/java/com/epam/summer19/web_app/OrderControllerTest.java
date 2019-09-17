package com.epam.summer19.web_app;

import com.epam.summer19.model.Order;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.service.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemInOrderService itemInOrderService;

    @Autowired
    private ItemService itemService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void listAllOrdersDTOTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/ordersdto")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                containsString("CafeMenu - Orders list")));

        Mockito.verify(orderService, Mockito.times(1)).findAllDTO();
    }

    @Test
    void gotoAddOrderPageTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/order")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("New order")));

        //Mockito.verify(orderService, Mockito.times(1)).findOrderById(Mockito.anyInt());
    }

    @Test
    void AddOrderTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("orderId", "1")
                        .param("orderEmployeeId", "6")
                        //.param("orderDateTime", "2019-09-15 23:00:01")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ordersdto"));

        Mockito.verify(orderService, Mockito.times(1)).add(Mockito.any(Order.class));
    }

    @Test
    void gotoEditOrderPageTest() throws Exception {
        Mockito.when(orderService.findOrderById(Mockito.anyInt())).thenReturn(createOrder(1)) ;

        mockMvc.perform(
                MockMvcRequestBuilders.get("/order/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("Edit order #")));

        Mockito.verify(orderService, Mockito.times(1)).findOrderById(Mockito.anyInt());
        Mockito.verify(itemInOrderService, Mockito.times(1)).findIioByOrderId(Mockito.anyInt());
        Mockito.verify(itemService, Mockito.times(1)).findAll();

    }

    @Test
    void updateOrderTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/order/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("orderId", "1")
                        .param("orderEmployeeId", "7")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ordersdto"));

        Mockito.verify(orderService, Mockito.times(1)).update(Mockito.any(Order.class));
    }

    @Test
    void deleteOrderTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/1/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ordersdto"));

        Mockito.verify(orderService, Mockito.times(1)).delete(Mockito.anyInt());
    }

    @AfterEach
    void afterEach() {
        Mockito.verifyNoMoreInteractions(itemInOrderService);
        Mockito.verifyNoMoreInteractions(orderService);
        Mockito.reset(orderService);
        Mockito.reset(itemInOrderService);
    }

    private static Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderEmployeeId(orderId+5);
        return order;
    }

}