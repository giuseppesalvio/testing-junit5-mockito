package com.salvio.controller;

import static com.salvio.repository.AziendaDipendentiRepositoryIntegrationTest.inserisciAziendaADB;
import static com.salvio.repository.AziendaDipendentiRepositoryIntegrationTest.inserisciDipendenteDB;
import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.dto.DipendenteDto;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class AziendaDipendentiTestE2E {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaE2EOk() throws Exception {
    String nomeAzienda="fincons";
  inserisciAziendaADB(jdbcTemplate, 10, "mrtmcl", nomeAzienda, "ITALIA", LocalDate.of(1983, 01, 01));

    inserisciDipendenteDB(jdbcTemplate, 10, "pppplt", "pippo", "pluto", "manager", 2000.00, LocalDate.of(2017, 01, 01));
    inserisciDipendenteDB(jdbcTemplate, 10, "mrossi", "mario", "rossi", "developer", 1000.00, LocalDate.of(2020, 01, 01));

    Gson gson= new Gson();




    ResultActions resultActions= mvc.perform(MockMvcRequestBuilders.get("/azienda/dipendenti/get-all-from").param("nomeAzienda",nomeAzienda))
        .andDo(print()).andExpect(status().isOk());


    String result= resultActions.andReturn().getResponse().getContentAsString();

    Type listaDipendenti = new TypeToken<ArrayList<DipendenteDto>>() {}.getType();
    List<DipendenteDto> dipendentiAzienda= gson.fromJson(result,listaDipendenti);


    DipendenteDto expected= new DipendenteDto("pippo","pluto","pppplt");


    Assertions.assertThat(dipendentiAzienda.get(0)).isEqualToComparingFieldByField(expected);
    Assertions.assertThat(dipendentiAzienda.get(0)).isEqualToComparingOnlyGivenFields(expected,"nome","cognome");
    Assertions.assertThat(dipendentiAzienda.get(0)).isEqualToIgnoringGivenFields(expected,"codiceFiscale");

  }


}