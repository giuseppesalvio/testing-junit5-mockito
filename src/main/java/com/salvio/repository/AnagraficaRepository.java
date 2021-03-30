package com.core.repository;

import com.core.entitys.Anagrafica;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnagraficaRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Anagrafica> getAll() {
    return jdbcTemplate.query(
        "select * from anagrafica ",
        (rs, rowNum) ->
            new Anagrafica(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("codiceFiscale")));
  }

  public int insert(Anagrafica anagrafica) {
    return jdbcTemplate.update(
        "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
        anagrafica.getId(),
        anagrafica.getNome(),
        anagrafica.getCognome(),
        anagrafica.getCodiceFiscale());
  }
}
