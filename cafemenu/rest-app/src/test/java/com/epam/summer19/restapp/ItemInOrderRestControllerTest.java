/**package com.epam.summer19.restapp;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class ItemInOrderRestControllerTest {

    @Autowired
    private ItemInOrderRestController iteminorderController;

    @Autowired
    private ItemInOrderService iteminorderService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(iteminorderController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void afterReset() {
        /** Mockito.verifyNoMoreInteractions(iteminorderService);
        Mockito.reset(iteminorderService);
    }


    @Test
    public void testItemInOrderAdd() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/iteminorder")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(createItemInOrder(1,2)))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(iteminorderService, Mockito.times(1)).add(any(ItemInOrder.class));
    }

    @Test
    public void testUpdateItemInOrder() throws Exception {
        ItemInOrder iteminorder = createItemInOrder(2,4);
        String json = new ObjectMapper().writeValueAsString(iteminorder);

        mockMvc.perform(put("/iteminorder")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());

        Mockito.verify(iteminorderService, Mockito.times(1)).update(any());
    }

    @Test
    public void testDeleteItemInOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/iteminorders/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(iteminorderService, Mockito.times(1)).delete(any());
    }


    @Test
    public void testItemInOrderFindAll() throws Exception {
        Mockito.when(iteminorderService.findAll()).thenReturn(Arrays.asList(createItemInOrder(1,2), createItemInOrder(2,3)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/iteminorders")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].iteminorderName", Matchers.is("ItemInOrder2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].iteminorderPrice", Matchers.is(2.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].iteminorderName", Matchers.is("ItemInOrder3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].iteminorderPrice", Matchers.is(2.2)))
        ;
        Mockito.verify(iteminorderService).findAll();
    }

    @Test
    public void testIioFindByOrderId() throws Exception {
        Mockito.when(iteminorderService.findIioByOrderId(2))
                .thenReturn(createItemInOrder(2,2,"Item",new BigDecimal("2.2"),2));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/iteminorders/2")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioOrderId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemName", Matchers.is("Item")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemPrice", Matchers.is(2.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemCount", Matchers.is(2)))
        ;
        Mockito.verify(iteminorderService, Mockito.times(1)).findIioByOrderId(2);
    }

    @Test
    public void testIioFindByOrderItemId() throws Exception {
        Mockito.when(iteminorderService.findIioByOrderId(2))
                .thenReturn(createItemInOrder(2,2,"Item",new BigDecimal("2.2"),2));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/iteminorders/2")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioOrderId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemName", Matchers.is("Item")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemPrice", Matchers.is(2.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iioItemCount", Matchers.is(2)))
        ;
        Mockito.verify(iteminorderService, Mockito.times(1)).findIioByOrderId(2);
    }

    private ItemInOrder createItemInOrder(int iioOrderId, int iioItemId,
                                          String iioItemName, BigDecimal iioItemPrice, int iioItemCount) {
        ItemInOrder iteminorder = new ItemInOrder();
        iteminorder.setIioOrderId(iioOrderId);
        iteminorder.setIioItemId(iioItemId);
        iteminorder.setIioItemName(iioItemName);
        iteminorder.setIioItemPrice(iioItemPrice);
        iteminorder.setIioItemCount(iioItemCount);
        return iteminorder;
    }

}**/