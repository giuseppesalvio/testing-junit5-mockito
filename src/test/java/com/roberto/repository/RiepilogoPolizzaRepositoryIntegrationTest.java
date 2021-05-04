package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.RiepilogoPolizzaCompleta;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class RiepilogoPolizzaRepositoryIntegrationTest {

  @Autowired
  private RiepilogoPolizzaRepository riepilogoPolizzaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaRiepilogoPolizzaRepoTestOk() {

    jdbcTemplate.update(
        "insert into riepilogoPolizza(numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) "
            + "values(1,9797,2222,8888,'2021-06-01',500.00)");

    Integer idAnagrafica=9797;
    List<RiepilogoPolizzaCompleta> listaOttenuta= riepilogoPolizzaRepository.getInfoPolizzeBy(idAnagrafica);

    Assertions.assertThat(listaOttenuta.size()).isEqualTo(1);

  }


}
