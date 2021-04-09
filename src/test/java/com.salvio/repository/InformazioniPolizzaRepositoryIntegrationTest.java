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
public class InformazioniPolizzaRepositoryIntegrationTest {

  @Autowired
  private InformazioniPolizzaRepository informazioniPolizzaRepository;


  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Test
  public void controlloExecutePolizzaRepository(){


    jdbcTemplate.update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(1,9999,9999,9999)");
    jdbcTemplate.update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(2,8888,9999,2222)");
    jdbcTemplate.update("insert into polizzaProva(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values(3,2222,2222,8888)");



    List<PolizzaProva> listaPolizzeRicevute =informazioniPolizzaRepository.getListaPolizzeAssociateAIdAnagrafica(9999);

    Assertions.assertThat(listaPolizzeRicevute.size()).isEqualTo(2);








  }


}
