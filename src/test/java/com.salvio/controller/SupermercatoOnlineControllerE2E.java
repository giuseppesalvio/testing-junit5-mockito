package com.salvio.controller;

import static com.salvio.persistor.ProdottiSupermercatoPersistor.inserisciProdottoSupermercato;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.entitys.ProdottoInfoBase;
import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import java.lang.reflect.Type;
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
public class SupermercatoOnlineControllerE2E {

  @Autowired
  private MockMvc mvc;


  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void controllerE2EOK() throws Exception {

    String codiceProdotto="P0001";
    String parametroOrdinamento="costoProdotto";
    String categoriaRichiesta="alimentare";

    inserisciProdottoSupermercato(jdbcTemplate, "P0001", "farina", 0.35, "ITALIA","2021-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0002", "latte", 0.60, "ITALIA","2021-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0003", "pasta", 0.55, "ITALIA","2022-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0004", "acqua", 0.25, "ITALIA","2025-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0005", "pentola", 5.00, "GERMANIA","null","cucina");


    inserisciDisponibilitaProdotto(jdbcTemplate,"P0001",100);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0002",200);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0003",400);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0004",1000);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0005",50);


    ResultActions resultActionsGetAll = mvc.perform(post("/getAllProducts")).andDo(print()).andExpect(status().isOk());
    String rispostaGetAll = resultActionsGetAll.andReturn().getResponse().getContentAsString();

    ResultActions resultActionsGetBy = mvc.perform(post("/getBy").content(codiceProdotto)).andDo(print()).andExpect(status().isOk());
    String rispostaGetBy= resultActionsGetBy.andReturn().getResponse().getContentAsString();

 /*   ResultActions resultActionsOrder=  mvc.perform(post("/getAscOrderBy").param(parametroOrdinamento)).andDo(print()).andExpect(status().isOk());
    String resultGetOrder = resultActionsOrder.andReturn().getResponse().getContentAsString();*/

   // ResultActions resultActionFromCategoria=  mvc.perform(post("/getAllFilterBy").param(categoriaRichiesta)).andDo(print()).andExpect(status().isOk());

    //deserializzazione risposte
    Gson gson = new Gson();

    //lista
    Type listaProdottiInfoBaseProva = new TypeToken<ArrayList<ProdottoInfoBaseProva>>() {}.getType();
    List<ProdottoInfoBaseProva> listaProdottiBaseOttenuta = gson.fromJson(rispostaGetAll, listaProdottiInfoBaseProva);

    //singolo Oggetto
    ProdottoInfoCompletaProva infoProdottoCercato=gson.fromJson(rispostaGetBy,ProdottoInfoCompletaProva.class);

   /* Type listaProdottiInfoBaseOrdinata= new TypeToken<ArrayList<ProdottoInfoBase>>() {}.getType();
    List<ProdottoInfoBase> listaProdottiBaseOrdinata= gson.fromJson(resultGetOrder,listaProdottiInfoBaseOrdinata);
*/



    Assertions.assertThat(listaProdottiBaseOttenuta.size()).isEqualTo(5);
    Assertions.assertThat(infoProdottoCercato.getNomeProdotto()).isEqualTo("farina");
   // Assertions.assertThat(listaProdottiBaseOrdinata.get(0).getNomeProdotto()).isEqualTo("acqua");

  }

  public void inserisciDisponibilitaProdotto(JdbcTemplate jdbcTemplate,String codice,Integer disponibilita){

    jdbcTemplate.update(
        "insert into InfoMagazzinoSupermercato(codiceProdotto,disponibilita) values(?,?) ",

        codice,
        disponibilita
    );

  }


}

