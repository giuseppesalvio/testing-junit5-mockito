package com.roberto.repository;

import static com.roberto.persistor.DipendentiPersistor.inserisciDipendente;

import com.roberto.StartApplication;
import com.roberto.entitys.Dipendente;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class GestioneDipendentiRepositoryIntegrationTest {


  @Autowired
  private GestioneDipendentiRepository gestioneDipendentiRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void controlloRepoOk(){

    inserisciDipendente(jdbcTemplate,9797,"A0001","developer","2021-03-01",1000.00);
    inserisciDipendente(jdbcTemplate,2222,"A0001","developer","2021-01-01",1000.00);
    inserisciDipendente(jdbcTemplate,8888,"A0001","manager","2019-01-01",2000.00);

    Integer idDipendente=9797;
    String codiceAzienda="A0001";


    Dipendente dipendente = gestioneDipendentiRepository.getDipendenteAttraverso(idDipendente);
    List<Dipendente> listaDipendentiAzienda =  gestioneDipendentiRepository.getListaDipendentiBy(codiceAzienda);



    Assertions.assertThat(dipendente.getRuolo()).isEqualTo("developer");
    Assertions.assertThat(listaDipendentiAzienda.size()).isEqualTo(3);
    Assertions.assertThat(listaDipendentiAzienda.get(0).getStipendio()).isEqualTo(1000.00);

  }


}
