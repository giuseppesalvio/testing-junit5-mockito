package com.salvio.repository;

import com.salvio.simulazioneprogettoarca.repository.TrovaAziendaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties.Jdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class TrovaAziendaRepositoryIntegrationTest {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TrovaAziendaRepository trovaAziendaRepository;

  @Test
  public void verificaRepositoryTestOk(){

    jdbcTemplate.update(
        "insert into AziendaDb( pIvaAzienda,nomeAzienda) values (1000,'fincons')");

    jdbcTemplate.update(
        "insert into AziendaDb( pIvaAzienda,nomeAzienda) values (15000,'star')");

    int pIva=1000;

    String nomeAzienda= trovaAziendaRepository.ottieniNomeAzienda(pIva);

    Assertions.assertThat(nomeAzienda).isEqualTo("fincons");

  }
}
