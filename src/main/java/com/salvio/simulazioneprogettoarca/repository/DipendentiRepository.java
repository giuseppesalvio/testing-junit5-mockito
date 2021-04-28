package com.salvio.simulazioneprogettoarca.repository;

import com.salvio.simulazioneprogettoarca.db.DipendenteDB;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DipendentiRepository {

  private final JdbcTemplate jdbcTemplate;

  public DipendentiRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<DipendenteDB>estraiListaDipendentiAttraverso(List<Integer> pIvaAziendeAssociate) {
    Integer codiceAzienda= pIvaAziendeAssociate.get(0);
    return jdbcTemplate.query("select * from dipendenteDB where pIvaAzienda=?",

         new Object[]{codiceAzienda},
         (rs, rowNum) ->
             new DipendenteDB(
             rs.getInt("pIvaAzienda"),
             rs.getString("codiceFiscale"),
             rs.getString("nome"),
             rs.getString("cognome"),
             rs.getString("ruolo"),
             rs.getDouble("stipendio"),
             rs.getDate("dataAssunzione").toLocalDate()


     ));


}}
