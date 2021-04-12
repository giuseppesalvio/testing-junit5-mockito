package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.PolizzaProva;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class OperazioniRepositoryIntegrationTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private OperazioniRepository operazioniRepository;


  @Test
  public void controlloExecuteOperazioniRepo(){


    jdbcTemplate.update("insert into operazioniProva(idOperazione,testoOperazione,numeroPolizzaInteressata,idAnagraficaInteressata) values(1,'ottieni dettagli polizza',1,9999) ");
    jdbcTemplate.update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(4,8888,8888,8888)");



    PolizzaProva polizzaProva = operazioniRepository.getPolizzaByNumeroPolizza(1);

    Assertions.assertThat(polizzaProva.getIdContraente()).isEqualTo(9999);

  }

}
