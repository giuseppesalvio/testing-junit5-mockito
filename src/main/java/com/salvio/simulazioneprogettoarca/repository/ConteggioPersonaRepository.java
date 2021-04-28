package com.salvio.simulazioneprogettoarca.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConteggioPersonaRepository {

  private final JdbcTemplate jdbcTemplate;

  public ConteggioPersonaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Integer estraiCardinalitaSesso(String sesso) {
   return jdbcTemplate.queryForObject("select count(*) as conteggio from PersonaTestDB where sesso=?",
        new Object[]{sesso},
        (rs, rowNum) ->
            rs.getInt("conteggio")
    );

  }
}
