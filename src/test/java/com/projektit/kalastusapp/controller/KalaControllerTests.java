package com.projektit.kalastusapp.controller;

import com.projektit.kalastusapp.models.Kala;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class KalaControllerTests {

    @Autowired
    private MockMvc mock;

    private static final String GET_PATH = "/api/kalat";

    @Test
    void getKalalistOk() throws Exception {
        ResultActions result = this.mock.perform(get(GET_PATH)).andExpect(status().isOk());
    }

    @Test
    void getOneKalaOk() throws Exception {
        ResultActions resultActions = this.mock.perform(get(GET_PATH + "/0")).andExpect(status().isOk());
    }
}