package com.salvio.repository;

import static com.salvio.controller.ConteggioPersonaControllerTestE2E.inserisciADB;

import com.salvio.simulazioneprogettoarca.db.PersonaTestDB;
import com.salvio.simulazioneprogettoarca.repository.ConteggioPersonaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class ConteggioPersonaRepositoryIntegrationTest {

  @Autowired
  private ConteggioPersonaRepository conteggioPersonaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void estraiCardinalitaSessoMaschile(){


    inserisciADB(jdbcTemplate,new PersonaTestDB(1,"pippo","pluto","pppplt","garibaldi","M"));
    inserisciADB(jdbcTemplate,new PersonaTestDB(2,"maria","rossi","mamama","garibaldi","F"));
    inserisciADB(jdbcTemplate,new PersonaTestDB(3,"maria","bianchi","frfr","garibaldi","F"));


    Integer numeroMaschi= conteggioPersonaRepository.estraiCardinalitaSesso("M");

    Assertions.assertThat(numeroMaschi).isEqualTo(1);


  }




}
