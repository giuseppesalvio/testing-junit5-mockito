package com.salvio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Sinistro;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class VerificaCompatibilitaSinistroECalcoloPremioE2E {

  @Autowired
  private MockMvc mockmvc;


  @Test
  public void controlloCompatibilitàOk() throws Exception {
    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      JSONArray jsonArray = new JSONArray();
      JSONObject item = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA

      item.put("giorno", "12/04/2021");
      item.put("ora", "14:00:00");
      jsonArray.put(item);
      jsonObject.put("dataSinistro", jsonArray);
      infoSinistro = jsonObject.toString();
    }
    catch(JSONException e){
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }


    ResultActions resultActions = mockmvc.perform(
        post("/verifica-compatibilità-sinistro-and-calcolo-premio-dovuto").content(infoSinistro)).andDo(print()).andExpect(status().isOk());


    String resultString =resultActions.andReturn().getResponse().getContentAsString();

    Gson gson= new Gson();
    Sinistro infoSinistroFornito= gson.fromJson(resultString,Sinistro.class);

    Assertions.assertThat(infoSinistroFornito.getTargaA()).isEqualTo("CE653TN");
    Assertions.assertThat(infoSinistroFornito.getTargaB()).isEqualTo("FL041PB");
    Assertions.assertThat(infoSinistroFornito.getAssicurazioneA()).isEqualTo("01333550323");
    Assertions.assertThat(infoSinistroFornito.getAssicurazioneA()).isEqualTo("03740811207");




  }


}
