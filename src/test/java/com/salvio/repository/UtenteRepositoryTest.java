package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.Utente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StartApplication.class)
class UtenteRepositoryTest {

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void estrai() {

        jdbcTemplate.update("insert into UTENTE(id,nome,cognome,codiceFiscale) values (9,'pinco','pallino','pincoCodFiscale')");

        Utente utente = utenteRepository.estrai("pincoCodFiscale");

        Assertions.assertThat(utente).isEqualToComparingFieldByField(
                new Utente(9,"pinco","pallino","pincoCodFiscale"));
    }
}
