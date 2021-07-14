package com.salvio;

import com.google.gson.Gson;
import com.roberto.esercizio.nuovo.dto.PersonaDto;
import com.salvio.dto.NomeCognomeDto1;
import com.salvio.dto.PersonaDto1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonaControllerTestE2E {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Gson gson = new Gson();
    @Test
    void personaGetByNomeCognome() throws Exception {
        jdbcTemplate.update(
                "insert into PERSONA(id ,nome   ,cognome ,codiceFiscale    ,indirizzo) values (1, 'ciccio', 'pasticcio', 'ciccioCF', 2)");

        NomeCognomeDto1 nomeCognomeDto1 = new NomeCognomeDto1("ciccio","pasticcio");

        ResultActions resultActionsDipendenti =
                mockMvc.perform(post("/person/getByNomeCognome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(nomeCognomeDto1)))
                        .andDo(print())
                .andExpect(status().isOk());

        String actual = resultActionsDipendenti.andReturn().getResponse().getContentAsString();
        PersonaDto1 expected = new PersonaDto1(1,3);
        Assertions.assertThat(actual).isEqualTo(expected);

    }


}
