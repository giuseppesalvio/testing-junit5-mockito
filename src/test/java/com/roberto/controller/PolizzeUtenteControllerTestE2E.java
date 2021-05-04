package com.roberto.controller;

import com.google.gson.Gson;
import com.roberto.StartApplication;
import com.roberto.entitys.PolizzaUtente;
import com.roberto.models.PolizzeUtenteFE;
import com.roberto.repository.RepositoryPolizzeUtente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
class PolizzeUtenteControllerTestE2E {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void endToEndTest_ControllerOk() throws Exception {
         /*
         //recupera tutte le polizze dell'utente tramite codicefiscale
          */
        //arrange
        jdbcTemplate.update(
                "insert into POLIZZE_UTENTE(id ,numeroPolizza ,utente_id ) values (1,'123',1)");
        jdbcTemplate.update(
                "insert into UTENTE(id,nome,cognome,codicefiscale) values (1, 'mario','rossi','codiceFiscaleTest')");

        //act
        ResultActions result =mockMvc.perform(get("/utente/polizzaUtenteByFc").param("codiceFiscale","CodiceFiscaleTest")).andDo(print()).andExpect(status().isOk());

        String resultAsString =result.andReturn().getRequest().getContentAsString();

        Gson gson=new Gson();
        PolizzeUtenteFE actual=gson.fromJson(resultAsString, PolizzeUtenteFE.class);
        //assert
        PolizzeUtenteFE expected=new PolizzeUtenteFE("123",Long.valueOf(1));
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
        //TODO finito questo, inizio a costruire il controller effettivo in maniera basica? in modo da avere l endpoint settato e dispibibile?
    }
}
