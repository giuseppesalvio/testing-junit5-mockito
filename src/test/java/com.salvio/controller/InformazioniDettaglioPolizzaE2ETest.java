package com.salvio.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.misc.Version.print;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.Polizza;
import com.salvio.entitys.PolizzaProva;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class InformazioniDettaglioPolizzaE2ETest {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void controllerFunzionamentoOk() throws Exception {

    String codiceFiscale = "123456";

    jdbcTemplate
        .update("insert into anagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(9999,'mario','rossi','123456')");
    jdbcTemplate.update(
        "insert into anagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(8888,'gennaro','esposito','gnnsps')");
    jdbcTemplate
        .update("insert into anagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(2222,'pippo','pluto','pppplt')");

    jdbcTemplate
        .update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(1,9999,9999,9999)");
    jdbcTemplate
        .update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(2,8888,9999,2222)");
    jdbcTemplate
        .update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(3,2222,2222,8888)");

    ResultActions resultActions = mvc.perform(post("/get-informazioni-dettaglio-polizza").content(codiceFiscale))
        .andExpect(status().isOk());

    String stringJsonResultDettaglioPolizza = resultActions.andReturn().getResponse().getContentAsString();

    Gson gson = new Gson();

    java.lang.reflect.Type listDettaglioPolizzaProva = new TypeToken<ArrayList<DettaglioPolizzaProva>>() {
    }.getType();

    List<DettaglioPolizzaProva> dettaglioPolizzaTrasformatoDaJsonString = gson
        .fromJson(stringJsonResultDettaglioPolizza, listDettaglioPolizzaProva);

    assertThat(dettaglioPolizzaTrasformatoDaJsonString.size()).isEqualTo(2);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(0).getPolizza().getNumeroPolizza()).isEqualTo(1);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(0).getContraente().getCodiceFiscale()).isEqualTo(codiceFiscale);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(0).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(0).getBeneficiario().getCodiceFiscale()).isEqualTo(codiceFiscale);

    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(1).getPolizza().getNumeroPolizza()).isEqualTo(2);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(1).getContraente().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(1).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
    assertThat(dettaglioPolizzaTrasformatoDaJsonString.get(1).getBeneficiario().getCodiceFiscale()).isNotEqualTo(codiceFiscale);


  }

  @Test
  public void controlloFunzionamentoNuoveFunzionalitaOk() throws Exception {

    String contentOperazioniRequest = "{\n"
        + "   \"idOperazione\":1,\n"
        + "   \"numeroPolizzaInteressata\":4,\n"
        + "   \"idAnagraficaInteressata\":9999\n"
        + "}";

    jdbcTemplate.update(
        "insert into operazioniProva(idOperazione,testoOperazione,numeroPolizzaInteressata,idAnagraficaInteressata) values (1, 'mostra dettagli polizza fornita',4,9999 )");
    jdbcTemplate
        .update("insert into PolizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values (4,8888,8888,8888)");

    ResultActions resultOperazioniSuPolizza = mvc
        .perform(post("/get-operazioni-su-polizza-richieste-da-id-anagrafica").content(contentOperazioniRequest))
        .andExpect(status().isOk());

    String stringJsonResultOperazioniSuPolizza = resultOperazioniSuPolizza.andReturn().getResponse().getContentAsString();

    Gson gson = new Gson();

    PolizzaProva polizzaRestituita = gson.fromJson(stringJsonResultOperazioniSuPolizza, PolizzaProva.class);

    Assertions.assertThat(polizzaRestituita.getIdContraente()).isEqualTo(8888);


  }


}

