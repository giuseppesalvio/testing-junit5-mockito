package com.roberto.repository;

import com.roberto.entitys.AnagraficaAzienda;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GestioneAnagraficheRepository {

  private final JdbcTemplate jdbcTemplate;

  public GestioneAnagraficheRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public AnagraficaAzienda getAnagraficaBy(Integer idAnagrafica) {

    return (AnagraficaAzienda) jdbcTemplate.queryForObject("select * from AnagraficaAziende where idAnagrafica=?", new Object[]{idAnagrafica},
        new BeanPropertyRowMapper(AnagraficaAzienda.class));
  }

  public AnagraficaAzienda getAnagraficaCorrelataA(String codiceFiscale) {
    return (AnagraficaAzienda) jdbcTemplate.queryForObject("select * from AnagraficaAziende where codiceFiscale=?", new Object[]{codiceFiscale},
        new BeanPropertyRowMapper(AnagraficaAzienda.class));
  }
}
