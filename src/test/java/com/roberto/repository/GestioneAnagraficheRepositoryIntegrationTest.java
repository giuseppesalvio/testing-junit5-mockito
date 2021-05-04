package com.roberto.repository;

import static com.roberto.persistor.AnagraficaAziendePersistor.inserisciAnagraficaAzienda;

import com.roberto.StartApplication;
import com.roberto.entitys.AnagraficaAzienda;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class GestioneAnagraficheRepositoryIntegrationTest {

  @Autowired
  private GestioneAnagraficheRepository gestioneAnagraficheRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaAnagraficheRepositoryTestOk(){

    inserisciAnagraficaAzienda(jdbcTemplate,9797,"emanuele","castagnaro","cstmnl");
    inserisciAnagraficaAzienda(jdbcTemplate,2222,"pippo","pluto","pppplt");
    inserisciAnagraficaAzienda(jdbcTemplate,8888,"gennaro","esposito","gnnsps");
    inserisciAnagraficaAzienda(jdbcTemplate,0000,"michele","moretti","mrtmcl");

    Integer idAnagrafica=9797;
    String codiceFiscale="mrtmcl";



    AnagraficaAzienda anagraficaAziendaById= gestioneAnagraficheRepository.getAnagraficaBy(idAnagrafica);
    AnagraficaAzienda anagraficaAziendaByCf =gestioneAnagraficheRepository.getAnagraficaCorrelataA(codiceFiscale);



    Assertions.assertThat(anagraficaAziendaById.getCodiceFiscale()).isEqualTo("cstmnl");
    Assertions.assertThat(anagraficaAziendaByCf.getIdAnagrafica()).isEqualTo(0000);





  }

}
