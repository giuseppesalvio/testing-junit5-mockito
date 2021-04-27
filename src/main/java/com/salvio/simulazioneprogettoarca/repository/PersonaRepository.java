package com.salvio.simulazioneprogettoarca.repository;

import com.salvio.entitys.InfoMagazzinoSupermercatoProva;
import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository {

  private final JdbcTemplate jdbcTemplate;

  public PersonaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<PersonaDB> estraiAttraverso(DatiAnagraficiDTO inputDto) {
    String sql = "select * from persona where nome=? and cognome=?";
    //   return jdbcTemplate.query("select * from Persona where nome=" + inputDto.getNome() + " and cognome= " + inputDto.getCognome(),
  return jdbcTemplate.query(sql, new Object[]{inputDto.getNome(), inputDto.getCognome()},
        (rs, rowNum) ->
            new PersonaDB(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("codiceFiscale"),
                rs.getInt("indirizzo")
            ));

  }
}