package com.epam.summer19.restapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class VersionControllerTest {

    private final static String VERSION = "1.0.0";

    @Autowired
    private VersionController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    /**
     * VersionController test
     *
     */
    @Test
    public void version() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/version")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("version")));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void cafemenurest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/cafemenurest")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("restReadme")));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void index() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("index")));
    }
}