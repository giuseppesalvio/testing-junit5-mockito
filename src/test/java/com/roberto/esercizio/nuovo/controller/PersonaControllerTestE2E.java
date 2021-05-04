package com.roberto.esercizio.nuovo.controller;

import com.google.gson.Gson;

import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.roberto.esercizio.nuovo.dto.PersonaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        NomeCognomeDto asd = new NomeCognomeDto("ciccio", "pasticcio");
        ResultActions resultActions =
                this.mockMvc.perform(
                        post("/persona/getByNomeCognome")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(asd))
                )
                        .andDo(print()).andExpect(status().isOk());


        String respo = resultActions.andReturn().getResponse().getContentAsString();
        PersonaDto actual = gson.fromJson(respo, PersonaDto.class);


        PersonaDto expected = new PersonaDto(1, 2);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

}
