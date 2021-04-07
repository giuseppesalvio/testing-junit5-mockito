package com.salvio.controller;

import com.salvio.StartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class E2EControllerPolizzaTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void controlloPolizzaOk() throws Exception{

        String cf="xxx";
        jdbcTemplate.update("\n" +
                "INSERT INTO polizzaTest(numeroPolizza, contraente, assicurato, beneficiario) VALUES\n" +
                "(1,'xxx','xxx','xxx'),\n" +
                "(2,'yyy','yyy','xxx'),\n" +
                "(3,'zzzzzzzzzzzzzzzz','xxxxxxxxxxxxxxxx','yyyyyyyyyyyyyyyy');\n");
       // MvcResult mvcResult= mvc.perform(get("/getPolizzeCollegateACodiceFiscale").param("cfInput",cf).contentType(MediaType.APPLICATION_JSON)).andReturn();

        ResultActions resultActions = this.mvc.perform(get("/getPolizzeCollegateACodiceFiscale").param("cfInput",cf)).andDo(print()).andExpect(status().isOk());
        String expected= "{\"numeroPolizza\":1,\"contraente\":\"xxx\",\"assicurato\":\"xxx\",\"beneficiario\":\"xxx\"}";
        String result= resultActions.andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(expected,result);


    }





}
