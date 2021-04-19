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

    jdbcTemplate.update(
        "insert into automobile(numeroTarga,codiceFiscaleProprietario,P_IvaAssicurazioneAssociata,numeroPolizzaAssociata) values('CE653TN','cstmnl','01333550323',1)");


   String targaDaVerificare="CE653TN";



    Automobile auto= automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(targaDaVerificare);

    Assertions.assertThat(auto.getCodiceFiscaleProprietario()).isEqualTo("cstmnl");



  }


}
