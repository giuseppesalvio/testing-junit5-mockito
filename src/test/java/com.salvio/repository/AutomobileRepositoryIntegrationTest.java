package com.salvio.repository;

import com.google.gson.Gson;
import com.salvio.StartApplication;
import com.salvio.entitys.Automobile;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class AutomobileRepositoryIntegrationTest {

  @Autowired
  private AutomobileRepository automobileRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void controlloEstraiDatiDaAutoRepo() {
    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA
      jsonObject.put("dataSinistro", "12-04-2021");


      infoSinistro = jsonObject.toString();
    }
    catch(JSONException e){
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }
    jdbcTemplate.update(
        "insert into automobile(numeroTarga,codiceFiscaleProprietario,P_IvaAssicurazioneAssociata,numeroPolizzaAssociata) values('CE653TN','cstmnl','01333550323',1)");


   String targaDaVerificare="CE653TN";



    Automobile auto= automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(targaDaVerificare);

    Assertions.assertThat(auto.getCodiceFiscaleProprietario()).isEqualTo("cstmnl");



  }


}
