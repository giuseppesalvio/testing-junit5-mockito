package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.salvio.StartApplication;
import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.entitys.Sinistro;
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
public class VerificaCompatibilitaSinistroECalcoloPremioE2E {

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void controlloCompatibilitaOk() throws Exception {
    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA
      jsonObject.put("dataSinistro", "2021-04-12");

      infoSinistro = jsonObject.toString();
    } catch (JSONException e) {
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }

    jdbcTemplate.update(
        "insert into automobile(numeroTarga,codiceFiscaleProprietario,P_IvaAssicurazioneAssociata,numeroPolizzaAssociata)values('CE653TN','cstmnl','01333550323',1)");

    jdbcTemplate.update(
        "insert into AnagraficaEstesa(idAnagrafica,nome,cognome,codiceFiscale) values (9797,'emanuele','castagnaro','cstmnl')");

    jdbcTemplate.update(
        "insert into polizzaEstesa( numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) values(1,9797,9797,9797,'2021-06-01',500.00)");
    jdbcTemplate.update(
        "insert into polizzaEstesa( numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) values(2,9797,2222,2222,'2021-06-01',500.00)");

    ResultActions resultActions = mockmvc.perform(
        post("/verifica-compatibilita-sinistro-and-calcolo-premio-dovuto").content(infoSinistro)).andDo(print())
        .andExpect(status().isOk());


    String resultString =resultActions.andReturn().getResponse().getContentAsString();

    Gson gson= new Gson();
    InfoPolizzaEstesa infoPolizzaEstesa= gson.fromJson(resultString,InfoPolizzaEstesa.class);

   Assertions.assertThat(infoPolizzaEstesa.getListaPolizzeCollegate().size()).isEqualTo(2);
   Assertions.assertThat(infoPolizzaEstesa.getTotalePremioDaVersare()).isEqualTo(1000);


  }


}
