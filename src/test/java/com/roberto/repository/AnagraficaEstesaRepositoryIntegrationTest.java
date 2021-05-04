package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.AnagraficaEstesa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class AnagraficaEstesaRepositoryIntegrationTest {

  @Autowired
  private AnagraficaEstesaRepository anagraficaEstesaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void controlloEstraiDatiAnagraficaDaCodiceFiscaleFornitoRepo(){

    jdbcTemplate.update("insert into AnagraficaEstesa(idAnagrafica,nome,cognome,codiceFiscale) values (9797,'emanuele','castagnaro','cstmnl')");


    AnagraficaEstesa anagraficaEstesaProva =anagraficaEstesaRepository.estraiDatiAnagraficaDaCodiceFiscaleFornito("cstmnl");

    Assertions.assertThat(anagraficaEstesaProva.getIdAnagrafica()).isEqualTo(9797);

  }

}
