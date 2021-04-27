package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.dto.RichiestaProdottoDto;
import com.salvio.simulazioneprogettoarca.dto.SupermercatoDto;
import java.lang.reflect.Type;
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

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class RichiestaProdottoSupermercatoE2E {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void verificaTestE2EOk() throws Exception {

    //manca da popolare il db H2


    inserisciSupermercato(jdbcTemplate, 1, "coop", "garibaldi", "baldaria", "verona");
    inserisciSupermercato(jdbcTemplate, 2, "lidl", "mazzini", "minerbe", "verona");
    inserisciSupermercato(jdbcTemplate, 3, "eurospin", "cavour", "legnago", "verona");
    inserisciSupermercato(jdbcTemplate, 4, "famila", "napoleone", "lonigo", "vicenza");

    inserisciMagazzino(jdbcTemplate, 1, 9999, "pane", 0.30, 100);
    inserisciMagazzino(jdbcTemplate, 1, 9998, "farina", 0.20, 1000);
    inserisciMagazzino(jdbcTemplate, 2, 8888, "pane", 0.40, 50);
    inserisciMagazzino(jdbcTemplate, 3, 5555, "pane", 0.45, 5);

    RichiestaProdottoDto richiestaProdottoDto = new RichiestaProdottoDto("pane", 10, "verona");
    Gson gson = new Gson();

    String stringDtoRequest = gson.toJson(richiestaProdottoDto);
    ResultActions resultActions = mvc.perform(
        post("/supermercato/get-lista-supermercati-validi-in-zona").contentType(MediaType.APPLICATION_JSON)
            .content(stringDtoRequest))
        .andDo(print()).andExpect(status().isOk());

    String result= resultActions.andReturn().getResponse().getContentAsString();
    Type listaSupermercatoDto= new TypeToken<ArrayList<SupermercatoDto>>() {}.getType();
    List<SupermercatoDto> listaSupermercatiValidi= gson.fromJson(result,listaSupermercatoDto);


    SupermercatoDto expected= new SupermercatoDto("coop","garibaldi","baldaria","verona");

    Assertions.assertThat(listaSupermercatiValidi.get(0)).isEqualToComparingFieldByField(expected);
    Assertions.assertThat(listaSupermercatiValidi.size()).isEqualTo(2);


  }


  private static void inserisciSupermercato(JdbcTemplate jdbcTemplate, Integer id, String nome, String via, String citta,
      String provincia) {

    jdbcTemplate.update("insert into SupermercatoDB(idSupermercato,nomeSupermercato,via,citta,provincia) values(?,?,?,?,?)",

        id,
        nome,
        via,
        citta,
        provincia
    );


  }

  private static void inserisciMagazzino(JdbcTemplate jdbcTemplate, Integer id, Integer codice, String nome, Double costo,
      Integer disponibilita) {

    jdbcTemplate.update(
        "insert into MagazzinoProdottiDB(idSupermercato,codiceProdotto,nomeProdotto,costoUnitario,disponibilita) values(?,?,?,?,?)",

        id,
        codice,
        nome,
        costo,
        disponibilita

    );


  }


}
