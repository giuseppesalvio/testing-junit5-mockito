package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.AnagraficaProva;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class InformazioniAnagraficaRepositoryIntegrationTest {


  @Autowired
  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;




  @Test
  public void controlloExecuteAnagraficaRepository(){

    String codiceFiscale="123456";
    Integer idAnagrafica=8888;
    jdbcTemplate.update("insert into AnagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(9999,'mario','rossi','123456')");
    jdbcTemplate.update("insert into AnagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(8888,'gennaro','esposito','gnnsps')");
    jdbcTemplate.update("insert into AnagraficaProva(idAnagrafica,nome,cognome,codiceFiscale) values(2222,'pippo','pluto','pppplt')");


    AnagraficaProva anagraficaDaCodiceFiscale =informazioniAnagraficaRepository.getAnagraficaDaCodiceFiscale(codiceFiscale);

    AnagraficaProva anagraficaDaIdAnagrafica= informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(idAnagrafica);





    Assertions.assertThat(anagraficaDaCodiceFiscale.getIdAnagrafica()).isEqualTo(9999);
    Assertions.assertThat(anagraficaDaIdAnagrafica.getNome()).isEqualTo("gennaro");






  }

}
