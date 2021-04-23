package com.salvio.controller;

import static com.salvio.persistor.PolizzaProvaPersistor.inserisciPolizzaProva;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.misc.Version.print;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.Polizza;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.AnagraficaRepositoryProva;
import java.io.UnsupportedEncodingException;
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
  public JdbcTemplate jdbcTemplate;

  @Autowired
  private AnagraficaRepositoryProva anagraficaRepositoryProva;

  @Test
  public void controllerFunzionamentoOk() throws Exception {

    AnagraficaProva anagraficaProva = new AnagraficaProva(9999, "mario", "rossi", "123456");
    inserisciAnagraficaProva(anagraficaProva);
    inserisciAnagraficaProva(new AnagraficaProva(8888, "gennaro", "esposito", "gnnsps"));
    inserisciAnagraficaProva(new AnagraficaProva(2222, "pippo", "pluto", "pppplt"));

    inserisciPolizzaProva(jdbcTemplate,new PolizzaProva(1, 9999, 9999, 9999));
    inserisciPolizzaProva(jdbcTemplate,new PolizzaProva(2, 8888, 9999, 2222));
    inserisciPolizzaProva(jdbcTemplate,new PolizzaProva(3, 2222, 2222, 8888));

    ResultActions resultActions = mvc
        .perform(post("/get-informazioni-dettaglio-polizza")
                .content(anagraficaProva.getCodiceFiscale()))
        .andExpect(status().isOk());

    List<DettaglioPolizzaProva> listaDettaglioPolizzaProva = getDettaglioPolizzaProva(resultActions);

    assertThat(listaDettaglioPolizzaProva.size()).isEqualTo(2);
    assertThat(listaDettaglioPolizzaProva.get(0).getPolizza().getNumeroPolizza()).isEqualTo(1);
    assertThat(listaDettaglioPolizzaProva.get(0).getContraente().getCodiceFiscale())
        .isEqualTo(anagraficaProva.getCodiceFiscale());
    assertThat(listaDettaglioPolizzaProva.get(0).getAssicurato().getCodiceFiscale())
        .isEqualTo(anagraficaProva.getCodiceFiscale());
    assertThat(listaDettaglioPolizzaProva.get(0).getBeneficiario().getCodiceFiscale())
        .isEqualTo(anagraficaProva.getCodiceFiscale());

    assertThat(listaDettaglioPolizzaProva.get(1).getPolizza().getNumeroPolizza()).isEqualTo(2);
    assertThat(listaDettaglioPolizzaProva.get(1).getContraente().getCodiceFiscale())
        .isNotEqualTo(anagraficaProva.getCodiceFiscale());
    assertThat(listaDettaglioPolizzaProva.get(1).getAssicurato().getCodiceFiscale())
        .isEqualTo(anagraficaProva.getCodiceFiscale());
    assertThat(listaDettaglioPolizzaProva.get(1).getBeneficiario().getCodiceFiscale())
        .isNotEqualTo(anagraficaProva.getCodiceFiscale());


  }

  private void inserisciAnagraficaProva(AnagraficaProva anagraficaProva) {
    anagraficaRepositoryProva.insert(anagraficaProva);
  }

  private List<DettaglioPolizzaProva> getDettaglioPolizzaProva(ResultActions resultActions) throws UnsupportedEncodingException {
    String stringJsonResultDettaglioPolizza = resultActions.andReturn().getResponse().getContentAsString();

    Gson gson = new Gson();

    java.lang.reflect.Type listDettaglioPolizzaProva = new TypeToken<ArrayList<DettaglioPolizzaProva>>() {
    }.getType();

    List<DettaglioPolizzaProva> dettaglioPolizzaTrasformatoDaJsonString = gson
        .fromJson(stringJsonResultDettaglioPolizza, listDettaglioPolizzaProva);
    return dettaglioPolizzaTrasformatoDaJsonString;
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

