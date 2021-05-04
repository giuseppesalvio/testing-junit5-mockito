package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.PolizzaUtente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StartApplication.class)
class RepositoryPolizzeUtenteTest {
    //todo  POSSO COPIARE INCOLLARE UN REPOSITORY INTERO E POI MODIFICARE? E STESSA COSA PER LE ALTRE CLASSI
    // TODO PRENDENDO

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RepositoryPolizzeUtente polizzaUtenteRepository;

    @Test
    void getPersonaBy() {

        jdbcTemplate.update(
                "insert into POLIZZA_UTENTE(id ,numeroPolizza ,utente_id ) values (1,'123',1)");
        jdbcTemplate.update(
                "insert into UTENTE(id,nome,cognome,codicefiscale) values (1, 'mario','rossi','testFscalCdeInput')");

        PolizzaUtente actual = polizzaUtenteRepository.getPolizzeutenteByFscalCode("testFscalCdeInput");

        assertThat(actual).isEqualToComparingFieldByField(
                new PolizzaUtente(Long.valueOf(1),"123",Long.valueOf(1)));
    }
}

