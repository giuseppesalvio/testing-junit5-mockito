package com.roberto.esercizio.nuovo.controller;

import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.roberto.esercizio.nuovo.dto.PersonaDto;
import com.roberto.esercizio.nuovo.service.PersonaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PersonaControllerTest {

    @InjectMocks
    PersonaController personaController;
    @Mock
    PersonaService personaService;

    @Test
    public void trovaPersonaBy() {

        PersonaDB personaDB = new PersonaDB(1,"ciccio","pasticcio","ciccioCF",2);
        NomeCognomeDto nomeCognomeDto = new NomeCognomeDto("ciccio", "pasticcio");
        when(personaService.getPersonaBy(nomeCognomeDto)).thenReturn(personaDB);

        PersonaDto personaDto = personaController.trovaPersonaBy(nomeCognomeDto);

        Assertions.assertThat(personaDto).isEqualToComparingFieldByField(new PersonaDto(1,2));
    }
}
