package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = StartApplication.class)
class PolizzaRepositoryTest {
    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Autowired
    private PolizzaRepository polizzaRepository;


    @Test
    void cercaByCodiceAnagrafica() {
        inserisciPolizza(1,9999,9999,9999);
        inserisciPolizza(2,9999,0000,2222);
        inserisciPolizza(3,123,456,890);
        inserisciPolizza(4,321,654,98);
        List<Polizza> listaPolizze = polizzaRepository.cercaByCodiceAnagrafica(9999);
        assertThat(listaPolizze.size()).isEqualTo(2);
    }

    private void inserisciPolizza(int id, int idContraente, int idAssicurato, int idBeneficiario) {
        jdbcTemplate.update(
                "INSERT INTO polizza (id, idContraente,idAssicurato,idBeneficiario) VALUES (?, ?, ?, ?)",
                id,
                idContraente,
                idAssicurato,
                idBeneficiario);
    }
}
