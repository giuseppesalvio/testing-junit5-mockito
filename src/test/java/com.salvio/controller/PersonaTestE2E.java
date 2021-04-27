package com.salvio.controller;

import static com.salvio.repository.PersonaRepositoryIntegrationTest.inserisciPersonaDB;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.dto.PersonaDTO;
import com.salvio.simulazioneprogettoarca.entity.Persona;
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
public class PersonaTestE2E {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void verificaTestE2EOk() throws Exception {
    DatiAnagraficiDTO inputDto = new DatiAnagraficiDTO("mario", "rossi");

    inserisciPersonaDB(jdbcTemplate,1,"mario","rossi","mrossi70",99999);
    inserisciPersonaDB(jdbcTemplate,2,"mario","rossi","mrossi95",33333);
    inserisciPersonaDB(jdbcTemplate,3,"luigi","bianchi","lbianchi60",66666);
    Gson gson = new Gson();

    ResultActions resultActions = mvc.perform(post("/persona/getByNomeCognome").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(inputDto))).andDo(print())
        .andExpect(status().isOk());

    String resultJson = resultActions.andReturn().getResponse().getContentAsString();

    Type listaPersona = new TypeToken<ArrayList<PersonaDTO>>() {
    }.getType();
    List<PersonaDTO> listaPersoneAssociateAInput = gson.fromJson(resultJson, listaPersona);

    Assertions.assertThat(listaPersoneAssociateAInput.size()).isEqualTo(2);
  }


}
