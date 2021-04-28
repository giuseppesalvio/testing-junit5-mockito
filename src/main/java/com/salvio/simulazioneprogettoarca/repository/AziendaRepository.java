package com.salvio.simulazioneprogettoarca.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AziendaRepository {

  private final JdbcTemplate jdbcTemplate;

  public AziendaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Integer> estraiAttraverso(String nomeAzienda) {

    return jdbcTemplate.query("select pIvaAzienda from AziendaDB where nomeAzienda=?",
        new Object[]{nomeAzienda},
        (rs, rowNum) ->
            rs.getInt("pIvaAzienda")
    );

  }
}
