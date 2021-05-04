package com.roberto.esercizio.nuovo.repository;

import com.roberto.StartApplication;
import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

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
