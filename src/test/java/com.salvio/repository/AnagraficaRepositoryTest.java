package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.Anagrafica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StartApplication.class)
public class AnagraficaRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnagraficaRepository anagraficaRepository;

    @Test
    public void getAll() {
        List<Anagrafica> result = anagraficaRepository.getAll();

        assertThat(result.get(0))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(1)
                                .nome("mario")
                                .cognome("rossi")
                                .codiceFiscale("mario13f9809a")
                                .build());
        assertThat(result.get(1))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(2)
                                .nome("ciccio")
                                .cognome("pasticcio")
                                .codiceFiscale("bello13f9809a")
                                .build());
        assertThat(result.get(2))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(3)
                                .nome("jonh")
                                .cognome("smith")
                                .codiceFiscale("giova13f9809a")
                                .build());
        assertThat(result.get(3))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(4)
                                .nome("gennaro")
                                .cognome("esposito")
                                .codiceFiscale("genna13f9809a")
                                .build());
    }

    @Test
    public void getAll2() {

        jdbcTemplate.update(
                "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
                5,
                "pinco",
                "pallino",
                "pinco13f9809a");
        jdbcTemplate.update(
                "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
                6,
                "giuseppe",
                "garibaldi",
                "gariba13f9809a");

        List<Anagrafica> result = anagraficaRepository.getAll();

        assertThat(result.get(4))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(5)
                                .nome("pinco")
                                .cognome("pallino")
                                .codiceFiscale("pinco13f9809a")
                                .build());
        assertThat(result.get(5))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(6)
                                .nome("giuseppe")
                                .cognome("garibaldi")
                                .codiceFiscale("gariba13f9809a")
                                .build());
    }
}
