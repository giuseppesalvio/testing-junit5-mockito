package com.roberto.repository;

import com.roberto.entitys.Automobile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AutomobileRepository {

  private final JdbcTemplate jdbcTemplate;

  public AutomobileRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Automobile verificaValiditaSinistroEdEstraiNumeroPolizza(String targaDaVerificare) {

   return(Automobile) jdbcTemplate.queryForObject("select * from automobile where numeroTarga=?",

        new Object[]{targaDaVerificare},
        (rs, rowNum) ->
            new Automobile(
                rs.getString("numeroTarga"),
                rs.getString("codiceFiscaleProprietario"),
                rs.getString("P_IvaAssicurazioneAssociata"),
                rs.getInt("numeroPolizzaAssociata")

                ));

  }
}
