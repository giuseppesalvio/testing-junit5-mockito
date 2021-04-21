package com.salvio.repository;

import com.salvio.entitys.RiepilogoAnagrafica;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RiepilogoAnagraficaRepository {

  private final JdbcTemplate jdbcTemplate;

  public RiepilogoAnagraficaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public RiepilogoAnagrafica getInfoAnagraficaBy(String codiceFiscale) {

    return (RiepilogoAnagrafica) jdbcTemplate.queryForObject("select * from riepilogoAnagrafica where codiceFiscale=?",
        new Object[]{codiceFiscale}, new BeanPropertyRowMapper(RiepilogoAnagrafica.class));

  }
}
