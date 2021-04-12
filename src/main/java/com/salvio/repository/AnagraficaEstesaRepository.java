package com.salvio.repository;

import com.salvio.entitys.AnagraficaEstesa;
import com.salvio.entitys.Automobile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnagraficaEstesaRepository {

  private JdbcTemplate jdbcTemplate;

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
