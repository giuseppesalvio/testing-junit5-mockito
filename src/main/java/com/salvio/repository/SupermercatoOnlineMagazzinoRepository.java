package com.salvio.repository;

import com.salvio.entitys.InfoMagazzinoSupermercatoProva;
import com.salvio.entitys.ProdottoSupermercatoProva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SupermercatoOnlineMagazzinoRepository {

  private final JdbcTemplate jdbcTemplate;

  public SupermercatoOnlineMagazzinoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public InfoMagazzinoSupermercatoProva estraiDisponibilita(String codiceProdotto) {

    return jdbcTemplate.queryForObject("select * from InfoMagazzinoSupermercato where codiceProdotto=?",
        new Object[]{codiceProdotto},
        (rs, rowNum) ->
            new InfoMagazzinoSupermercatoProva(
                rs.getString("codiceProdotto"),
                rs.getInt("disponibilita")
            )
        );
  }
}
