package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TrovaAziendaE2E {


  @Autowired
  private MockMvc mvc;


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void happyPath() throws Exception {

    jdbcTemplate.update(
        "insert into AziendaDb( pIvaAzienda,nomeAzienda) values (1000,'fincons')");

    jdbcTemplate.update(
        "insert into AziendaDb( pIvaAzienda,nomeAzienda) values (15000,'star')");

    int pIva = 1000;
    ResultActions resultActions = mvc.perform(
        MockMvcRequestBuilders.get("/get-nome-azienda-from").param("pIva", String.valueOf(pIva)))
        .andDo(print()).andExpect(status().isOk());
    String result = resultActions.andReturn().getResponse().getContentAsString();

    String expected = "Messaggio: l'azienda si chiama: fincons";
    Assertions.assertThat(result).isEqualTo(expected);

  }


}
