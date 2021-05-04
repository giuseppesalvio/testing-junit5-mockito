package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.PolizzaEstesa;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class PolizzaEstesaRepositoryIntegrationTest {

  @Autowired
  private PolizzaEstesaRepository polizzaEstesaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void controlloEstraiDatiPolizzaFornitaDaRepo(){

    jdbcTemplate.update("insert into polizzaEstesa( numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) values(1,9797,9797,9797,'01-06-2021',500.00)");
    jdbcTemplate.update("insert into polizzaEstesa( numeroPolizza,idContraente,idAssicurato,idBeneficiario,dataProxQuietanzamento,importoQuietanzamento) values(2,9797,2222,2222,'01-06-2021',500.00)");

   PolizzaEstesa polizzaEstesaProva= polizzaEstesaRepository.estraiDatiPolizzaFornita(1);
   List<PolizzaEstesa> listaPolizzeCollegate=polizzaEstesaRepository.ottieniPolizzeConContraenteIdFornito(9797);

   Assertions.assertThat(polizzaEstesaProva.getIdBeneficiario()).isEqualTo(9797);
   Assertions.assertThat(listaPolizzeCollegate.size()).isEqualTo(2);


  }

}
