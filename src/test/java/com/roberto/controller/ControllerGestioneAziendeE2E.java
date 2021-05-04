package com.roberto.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roberto.StartApplication;
import com.roberto.response.AziendaAssociataResponse;
import com.roberto.response.DipendentiAziendaResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.roberto.persistor.AnagraficaAziendePersistor.inserisciAnagraficaAzienda;
import static com.roberto.persistor.AziendaPersistor.inserisciAzienda;
import static com.roberto.persistor.DipendentiPersistor.inserisciDipendente;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class ControllerGestioneAziendeE2E {


  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verficaFunzionamentoE2EOk() throws Exception {
    String codiceAzienda = "A0001";
    String codiceFiscale = "cstmnl";

    Integer numeroDiDipendentiAspettato = 3;
    Integer numeroAziendeAspettato = 1;


    inserisciAnagraficaAzienda(jdbcTemplate,9797,"emanuele","castagnaro","cstmnl");
    inserisciAnagraficaAzienda(jdbcTemplate,2222,"pippo","pluto","pppplt");
    inserisciAnagraficaAzienda(jdbcTemplate,8888,"gennaro","esposito","gnnsps");
    inserisciAnagraficaAzienda(jdbcTemplate,0000,"michele","moretti","mrtmcl");

   inserisciAzienda(jdbcTemplate,"A0001","FinconsGroup","mrtmcl","1983-01-01","ITALIA");

    inserisciDipendente(jdbcTemplate,9797,"A0001","developer","2021-03-01",1000.00);
    inserisciDipendente(jdbcTemplate,2222,"A0001","developer","2021-01-01",1000.00);
    inserisciDipendente(jdbcTemplate,8888,"A0001","manager","2019-01-01",2000.00);



    ResultActions resultActionsDipendenti = mockmvc.perform(post("/getAllDependentFrom").content(codiceAzienda)).andDo(print())
        .andExpect(status().isOk());

    ResultActions resultActionsAziende = mockmvc.perform(post("/getAziendeAssociateA").content(codiceFiscale)).andDo(print())
        .andExpect(status().isOk());

    String jsonResponseDipendenti = resultActionsDipendenti.andReturn().getResponse().getContentAsString();

    String jsonResponseAziende = resultActionsAziende.andReturn().getResponse().getContentAsString();

    Gson gson = new Gson();

    //modo per deserializzare un json string in una lista di oggetti
    Type listaDipendentiAziendaResponse = new TypeToken<ArrayList<DipendentiAziendaResponse>>() {}.getType();
    List<DipendentiAziendaResponse> listaDipendenti=gson.fromJson(jsonResponseDipendenti,listaDipendentiAziendaResponse);


    Type listaAziendaAssociataResponse= new TypeToken<ArrayList<AziendaAssociataResponse>>() {}.getType();
    List<AziendaAssociataResponse> listaAziende=gson.fromJson(jsonResponseAziende,listaAziendaAssociataResponse ) ;

    Assertions.assertThat(listaDipendenti.size()).isEqualTo(numeroDiDipendentiAspettato);

    Assertions.assertThat(listaAziende.size()).isEqualTo(numeroAziendeAspettato);


    Assertions.assertThat(listaAziende.get(0).getMonteStipendiTotale()).isEqualTo(4000.00);


  }


}

