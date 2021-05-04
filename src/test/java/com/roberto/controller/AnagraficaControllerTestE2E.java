package com.roberto.controller;

import com.roberto.StartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
class AnagraficaControllerTestE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTotaleAccountBancaPost() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/getAll")).andDo(print()).andExpect(status().isOk());
    }
}
