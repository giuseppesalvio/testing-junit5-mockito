package com.roberto.repository;


import com.roberto.entitys.PolizzaProva;
import com.roberto.entitys.PolizzaTest;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class PolizzaRepositoryProva {

  private final JdbcTemplate jdbcTemplate;

  public PolizzaRepositoryProva(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public PolizzaTest chiamaEstraiPolizzaDaRepo(String cfInput){

    return jdbcTemplate.queryForObject("select * from polizzaTest where contraente=?", new Object[]{cfInput
        },
        new BeanPropertyRowMapper<>(PolizzaTest.class));
  }



  public List<PolizzaProva> ottieniListaPolizzeAssociateAIdAnagrafica(Integer idAnagrafica) {
    return null;
  }
}
