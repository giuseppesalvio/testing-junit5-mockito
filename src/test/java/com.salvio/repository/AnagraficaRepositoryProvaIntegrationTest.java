package com.salvio.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.salvio.StartApplication;
import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.AnagraficaProva;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class AnagraficaRepositoryProvaIntegrationTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private AnagraficaRepositoryProva anagraficaRepositoryProva;

  @Test
  public void getAllAnagraficheProva() {
    List<AnagraficaProva> result = anagraficaRepositoryProva.getAll();

    assertThat(result.get(0))
        .isEqualTo(
            AnagraficaProva.builder()
                .idAnagrafica(1)
                .nome("mario")
                .cognome("rossi")
                .codiceFiscale("mario13f9809a")
                .build());
    assertThat(result.get(1))
        .isEqualTo(
            AnagraficaProva.builder()
                .idAnagrafica(2)
                .nome("ciccio")
                .cognome("pasticcio")
                .codiceFiscale("bello13f9809a")
                .build());
    assertThat(result.get(2))
        .isEqualTo(
            AnagraficaProva.builder()
                .idAnagrafica(3)
                .nome("jonh")
                .cognome("smith")
                .codiceFiscale("giova13f9809a")
                .build());
    assertThat(result.get(3))
        .isEqualTo(
            AnagraficaProva.builder()
                .idAnagrafica(4)
                .nome("gennaro")
                .cognome("esposito")
                .codiceFiscale("genna13f9809a")
                .build());
  }


}




