package com.salvio.esercizio.nuovo.controller;

import com.google.gson.Gson;
import com.salvio.StartApplication;

import com.salvio.entitys.InfoPolizzaCompletaRiepilogo;
import com.salvio.esercizio.nuovo.dto.NomeCognomeDto;
import com.salvio.esercizio.nuovo.dto.PersonaDto;
import com.salvio.response.DipendentiAziendaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.assertj.core.api.Assertions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
class PersonaControllerTestE2E {

    @Autowired
    private MockMvc mockMvc;
    Gson gson = new Gson();

    @Test
    void personaGetByNomeCognome() throws Exception {
        NomeCognomeDto asd = new NomeCognomeDto("ciccio", "pasticcio");
        ResultActions resultActions =
                this.mockMvc.perform(
                        post("/persona/getByNomeCognome")
                        .content(gson.toJson(asd))
                )
                .andDo(print()).andExpect(status().isOk());


        String respo = resultActions.andReturn().getResponse().getContentAsString();
        PersonaDto actual=gson.fromJson(respo,PersonaDto.class);


        PersonaDto expected = new PersonaDto(1, 2);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

}
