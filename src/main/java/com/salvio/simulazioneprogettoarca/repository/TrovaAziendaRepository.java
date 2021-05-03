package com.salvio.simulazioneprogettoarca.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrovaAziendaRepository {

  private final JdbcTemplate jdbcTemplate;

  public TrovaAziendaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public String ottieniNomeAzienda(int pIva) {

   return jdbcTemplate.queryForObject("select nomeAzienda from aziendaDB where pIvaAzienda=? ",

       new Object[]{pIva},
       (rs, rowNum) ->
           rs.getString("nomeAzienda")
   );

  }
}
