package com.roberto.repository;

import com.roberto.entitys.Azienda;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GestioneAziendeRepository {

  private final JdbcTemplate jdbcTemplate;

  public GestioneAziendeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Azienda getInfoAziendaBy(String codiceAzienda) {
    return(Azienda) jdbcTemplate.queryForObject("select * from Azienda where codiceAzienda=?",
        new Object[]{codiceAzienda},
        new BeanPropertyRowMapper(Azienda.class)
    );
  }
}
