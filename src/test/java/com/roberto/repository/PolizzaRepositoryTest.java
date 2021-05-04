package com.roberto.repository;

import com.roberto.StartApplication;
import com.roberto.entitys.Polizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.roberto.persistor.PolizzaPersistor.inserisciPolizza;
import static org.assertj.core.api.Assertions.assertThat;


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
    assertThat(listaPolizze.get(0)).isEqualTo(new Polizza(1, 9999, 9999, 9999));
    assertThat(listaPolizze.get(1)).isEqualTo(new Polizza(2, 9999, 8888, 2222));
  }




}
