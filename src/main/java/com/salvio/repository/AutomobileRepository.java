package com.salvio.repository;

import com.google.gson.Gson;
import com.salvio.entitys.Automobile;
import com.salvio.entitys.Sinistro;
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
