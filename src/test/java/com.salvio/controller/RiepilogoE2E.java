package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

import com.google.gson.Gson;
import com.salvio.StartApplication;
import com.salvio.entitys.InfoPolizzaCompletaRiepilogo;
import com.salvio.entitys.SinistroRiepilogo;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class RiepilogoE2E {

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void controlloFunzionalitaOk() throws Exception {

    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");
      jsonObject.put("assicurazioneB", "03740811207");
      jsonObject.put("dataSinistro", "2021-04-20");

      infoSinistro = jsonObject.toString();

    } catch (JSONException e) {
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }

    jdbcTemplate.update(
        "insert into riepilogoAutomobile(numeroTarga,codiceFiscaleProprietario,codiceAssicurazione,numeroPolizzaAssociata) "
            + "values ('CE653TN','cstmnl','01333550323',1)");

    jdbcTemplate.update(
        "insert into RiepilogoAnagrafica(idAnagrafica,nome,cognome,codiceFiscale) values(9797,'emanuele','castagnaro','cstmnl')");


    jdbcTemplate.update(
        "insert into riepilogoPolizza(numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) "
            + "values(1,9797,2222,8888,'2021-06-01',500.00)");


    ResultActions resultActions = mockmvc.perform(post("/getRiepilogo").content(infoSinistro)).andDo(print())
        .andExpect(status().isOk());

    String jsonInfoSinistro = resultActions.andReturn().getResponse().getContentAsString();

    Gson gson = new Gson();

    InfoPolizzaCompletaRiepilogo infoPolizzaCompletaRiepilogo = gson
        .fromJson(jsonInfoSinistro, InfoPolizzaCompletaRiepilogo.class);

    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getListaPolizze().size()).isEqualTo(1);
    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getTotaleImportoDaVersare()).isEqualTo(500.00);


  }


}
