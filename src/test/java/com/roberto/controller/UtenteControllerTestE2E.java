package com.roberto.controller;

import com.google.gson.Gson;
import com.roberto.StartApplication;
import com.roberto.models.UtenteFE;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class UtenteControllerTestE2E {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    void happyPath() throws Exception {

        jdbcTemplate.update(
                "insert into UTENTE(id,nome,cognome,codiceFiscale) values (1, 'ciccio','pasticcio','ciccioCodFis')");
        jdbcTemplate.update(
                "insert into UTENTE(id,nome,cognome,codiceFiscale) values (2, 'mario','rossi','rossi1')");

        ResultActions resultActions = this.mockMvc.perform(
                get("/utente/getByCodiceFiscale")
                        .param("codiceFiscale", "ciccioCodFis")
        ).andDo(print()).andExpect(status().isOk());

        String result = resultActions.andReturn().getResponse().getContentAsString();
        Gson gson = new Gson();
        UtenteFE actual = gson.fromJson(result, UtenteFE.class);

        UtenteFE expected = new UtenteFE("ciccio", "pasticcio");
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
