package com.salvio.repository;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PolizzaRepository {

  private final JdbcTemplate jdbcTemplate;

  public PolizzaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public List<Polizza> cercaByCodiceAnagrafica(Integer idAnagrafica) {

    return jdbcTemplate.query("select * from polizza where idContraente=? or idAssicurato = ? or idBeneficiario=?",
        new Object[]{idAnagrafica, idAnagrafica, idAnagrafica},
        (rs, rowNum) ->
            new Polizza(
                rs.getInt("id"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario")));

  }

  /*
   */


}