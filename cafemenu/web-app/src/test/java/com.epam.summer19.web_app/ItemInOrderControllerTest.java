package com.epam.summer19.web_app;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
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
class ItemInOrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private ItemInOrderService itemInOrderService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void AddItemInOrderTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/iteminorder")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("iioOrderId", "1")
                        .param("iioItemId", "3")
                        .param("iioItemName", "Nuggets")
                        .param("iioItemPrice", "3.0")
                        .param("iioItemCount", "2")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/order/1"));

        Mockito.verify(itemInOrderService, Mockito.times(1)).add(Mockito.any(ItemInOrder.class));
    }

    @Test
    void deleteItemInOrderByIdTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/iteminorders/1/3/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/order/1"));

        Mockito.verify(itemInOrderService, Mockito.times(1)).delete(Mockito.anyInt(), Mockito.anyInt());
    }

}