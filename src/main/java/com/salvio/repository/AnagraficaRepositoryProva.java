package com.salvio.repository;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.AnagraficaProva;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnagraficaRepositoryProva {

  private final JdbcTemplate jdbcTemplate;

  public List<AnagraficaProva> getAll() {
    return jdbcTemplate.query(
        "select * from anagraficaProva ",
        (rs, rowNum) ->
            new AnagraficaProva(
                rs.getInt("idAnagrafica"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("codiceFiscale")));
  }

  public int insert(AnagraficaProva anagraficaProva) {
    return jdbcTemplate.update(
        "INSERT INTO anagraficaProva (idAnagrafica, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
        anagraficaProva.getIdAnagrafica(),
        anagraficaProva.getNome(),
        anagraficaProva.getCognome(),
        anagraficaProva.getCodiceFiscale());
  }



  public AnagraficaProva getAnagraficaDaCodiceFiscale(String codiceFiscale) {

    return (AnagraficaProva) jdbcTemplate.queryForObject("select * from anagraficaprova where codiceFiscale = ? ",
        new Object[]{codiceFiscale}, new BeanPropertyRowMapper(AnagraficaProva.class));

  }
}
