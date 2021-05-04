package com.roberto.repository;

import com.roberto.entitys.AnagraficaEstesa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnagraficaEstesaRepository {

  private final JdbcTemplate jdbcTemplate;

  public AnagraficaEstesaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public AnagraficaEstesa estraiDatiAnagraficaDaCodiceFiscaleFornito(String codiceFiscaleProprietario) {

  return jdbcTemplate.queryForObject("select * from anagraficaEstesa where codiceFiscale=?",
      new Object[]{codiceFiscaleProprietario},
      (rs, rowNum) ->
          new AnagraficaEstesa(
              rs.getInt("idAnagrafica"),
              rs.getString("nome"),
              rs.getString("cognome"),
              rs.getString("codiceFiscale")

          ));



  }
}
