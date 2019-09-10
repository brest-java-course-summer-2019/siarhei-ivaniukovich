package com.epam.summer19.restapp;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class ItemRestControllerTest {

    @Autowired
    private ItemRestController itemController;

    @Autowired
    private ItemService itemService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void afterReset() {
        /** Mockito.verifyNoMoreInteractions(itemService); **/
        Mockito.reset(itemService);
    }


    @Test
    public void testItemAdd() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/items")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(createItem(1,2)))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(itemService, Mockito.times(1)).add(any(Item.class));
    }

    @Test
    public void testUpdateItem() throws Exception {
        Item item = createItem(2,4);
        String json = new ObjectMapper().writeValueAsString(item);

        mockMvc.perform(put("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());

        Mockito.verify(itemService, Mockito.times(1)).update(any());
    }

    @Test
    public void testDeleteItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(itemService, Mockito.times(1)).delete(any());
    }


    @Test
    public void testItemFindAll() throws Exception {
        Mockito.when(itemService.findAll()).thenReturn(Arrays.asList(createItem(1,2), createItem(2,3)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/items")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].itemName", Matchers.is("Item2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].itemPrice", Matchers.is(2.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].itemName", Matchers.is("Item3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].itemPrice", Matchers.is(2.2)))
        ;
        Mockito.verify(itemService).findAll();
    }

    @Test
    public void testItemFindById() throws Exception {
        Mockito.when(itemService.findItemById(1)).thenReturn(createItem(1,5));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/items/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemName", Matchers.is("Item5")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemPrice", Matchers.is(2.2)))
        ;
        Mockito.verify(itemService, Mockito.times(1)).findItemById(1);
    }

    private Item createItem(int itemId, int namePostfix) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName("Item" + namePostfix);
        item.setItemPrice(new BigDecimal("2.2"));
        return item;
    }

}