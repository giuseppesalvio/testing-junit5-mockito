package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.RiepilogoAnagrafica;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class RiepilogoAnagraficaRepositoryIntegrationTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private RiepilogoAnagraficaRepository riepilogoAnagraficaRepository;

  @Test
  public void funzionamentoAnagraficaRepoTestOk() {

    String codiceFiscale="cstmnl";


    jdbcTemplate.update(
        "insert into RiepilogoAnagrafica(idAnagrafica,nome,cognome,codiceFiscale) values(9797,'emanuele','castagnaro','cstmnl')");
 jdbcTemplate.update(
        "insert into RiepilogoAnagrafica(idAnagrafica,nome,cognome,codiceFiscale) values(2222,'pippo','pluto','pppplt')");
 jdbcTemplate.update(
        "insert into RiepilogoAnagrafica(idAnagrafica,nome,cognome,codiceFiscale) values(8888,'gennaro','esposito','gnnsps')");

    RiepilogoAnagrafica riepilogoAnagrafica= riepilogoAnagraficaRepository.getInfoAnagraficaBy(codiceFiscale);

    Assertions.assertThat(riepilogoAnagrafica.getIdAnagrafica()).isEqualTo(9797);


  }

}
