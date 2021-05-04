package com.roberto.esercizio.nuovo.service;

import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.roberto.esercizio.nuovo.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PersonaServiceTest {

    @InjectMocks
    PersonaService personaService;

    @Mock
    PersonaRepository personaRepository;

    @Test
    void getPersonaBy() {
        NomeCognomeDto nomeCognome = new NomeCognomeDto("ciccio","pasticcio");
        when(personaRepository.getPersonaBy(nomeCognome))
                .thenReturn(new PersonaDB(1,"ciccio","pasticcio","ciccioCF",2));

        personaService.getPersonaBy(nomeCognome);

        verify(personaRepository).getPersonaBy(nomeCognome);
    }
}
