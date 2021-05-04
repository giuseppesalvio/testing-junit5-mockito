package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.RiepilogoAutomobile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class RiepilogoAutomobileRepositoryIntegrationTest {

  @Autowired
  private RiepilogoAutomobileRepository riepilogoAutomobileRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void funzionamentoAutomobileRepoTestOk() {

    jdbcTemplate.update(
        "insert into riepilogoAutomobile(numeroTarga,codiceFiscaleProprietario,codiceAssicurazione,numeroPolizzaAssociata) "
            + "values ('CE653TN','cstmnl','01333550323',1)");

    String targaDaValidare="CE653TN";
   RiepilogoAutomobile riepilogoAutomobile= riepilogoAutomobileRepository.getInfoAutomobileBy(targaDaValidare);

    Assertions.assertThat(riepilogoAutomobile.getNumeroPolizzaAssociata()).isEqualTo(1);

  }


}
