package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.salvio.persistor.PolizzaPersistor.inserisciPolizza;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = StartApplication.class)
class PolizzaRepositoryTest {

  @Autowired
  public JdbcTemplate jdbcTemplate;
  @Autowired
  private PolizzaRepository polizzaRepository;


  @Test
  void cercaByCodiceAnagraficaTest() {
    inserisciPolizza(jdbcTemplate,1, 9999, 9999, 9999);
    inserisciPolizza(jdbcTemplate,2, 9999, 8888, 2222);
    inserisciPolizza(jdbcTemplate,3, 8888, 8888, 2222);
    inserisciPolizza(jdbcTemplate,4, 2222, 2222, 2222);

    List<Polizza> listaPolizze = polizzaRepository.cercaByCodiceAnagrafica(9999);


    assertThat(listaPolizze.size()).isEqualTo(2);
  }




}
