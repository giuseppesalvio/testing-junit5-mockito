package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.db.PersonaTestDB;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class ConteggioPersonaControllerTestE2E {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaTestE2E() throws Exception {

    inserisciADB(jdbcTemplate,new PersonaTestDB(1,"pippo","pluto","pppplt","garibaldi","M"));
    inserisciADB(jdbcTemplate,new PersonaTestDB(2,"maria","rossi","mamama","garibaldi","F"));
    inserisciADB(jdbcTemplate,new PersonaTestDB(3,"maria","bianchi","frfr","garibaldi","F"));


    ResultActions resultActions = mvc.perform(get("/persona/get-conteggio-numerosita-sesso")).andDo(print())
        .andExpect(status().isOk());

    String result = resultActions.andReturn().getResponse().getContentAsString();

    String expected="il numero di persone di sesso maschile e: 1, il numero di persone di sesso femminile e: 2";
    Assertions.assertThat(result).isEqualTo(expected);


  }

  public static void inserisciADB(JdbcTemplate jdbcTemplate, PersonaTestDB personaTestDB){
    jdbcTemplate.update("insert into PersonaTestDb(id,nome,cognome,codiceFiscale,indirizzo,sesso) values("
        + "" + personaTestDB.getId() +","
        + "'" +  personaTestDB.getNome() +"',"
        + "'" + personaTestDB.getCognome() +"',"
        + "'" + personaTestDB.getCodiceFiscale() +"',"
        + "'" + personaTestDB.getIndirizzo() +"',"
        + "'" + personaTestDB.getSesso() +"'"
        + ")");

  }


}
