package com.salvio.esercizio.nuovo.repository;

import com.salvio.StartApplication;
import com.salvio.esercizio.nuovo.db.PersonaDB;
import com.salvio.esercizio.nuovo.dto.NomeCognomeDto;
import com.salvio.repository.RiepilogoAnagraficaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StartApplication.class)
class PersonaRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonaRepository personaRepository;

    @Test
    void getPersonaBy() {

        jdbcTemplate.update(
                "insert into PERSONA(id ,nome   ,cognome ,codiceFiscale    ,indirizzo) values (1, 'ciccio', 'pasticcio', 'ciccioCF', 2)");

        PersonaDB actual = personaRepository.getPersonaBy(new NomeCognomeDto("ciccio", "pasticcio"));

        assertThat(actual).isEqualToComparingFieldByField(
                        new PersonaDB(1, "ciccio", "pasticcio", "ciccioCF", 2));
    }
}
