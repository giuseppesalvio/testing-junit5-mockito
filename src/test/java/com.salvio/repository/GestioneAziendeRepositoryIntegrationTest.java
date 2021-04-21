package com.salvio.repository;

import static com.salvio.persistor.AziendaPersistor.inserisciAzienda;

import com.salvio.StartApplication;
import com.salvio.entitys.Azienda;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class GestioneAziendeRepositoryIntegrationTest {

  @Autowired
  private GestioneAziendeRepository gestioneAziendeRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void verificaAziendaRepoTestOk() {
    String codiceAzienda = "A0001";

    inserisciAzienda(jdbcTemplate,
           "A0001","FinconsGroup","mrtmcl","1983-01-01","ITALIA"
            );

    Azienda aziendaCorrelata = gestioneAziendeRepository.getInfoAziendaBy(codiceAzienda);

    Assertions.assertThat(aziendaCorrelata.getCodiceFiscaleProprietarioAzienda()).isEqualTo("mrtmcl");
  }


}
