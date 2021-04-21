package com.salvio.repository;

import com.salvio.entitys.Dipendente;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GestioneDipendentiRepository {

  private final JdbcTemplate jdbcTemplate;

  public GestioneDipendentiRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public List<Dipendente> getListaDipendentiBy(String codiceAzienda)
  {
    return jdbcTemplate.query("select * from Dipendente where codiceAzienda=?",
        new Object[]{codiceAzienda},
        (rs, rowNum) ->
            new Dipendente(
                rs.getInt("idDipendente"),
                rs.getString("codiceAzienda"),
                rs.getString("ruolo"),
                rs.getString("dataAssunzione"),
                rs.getDouble("stipendio")
            )
        );
  }

  public Dipendente getDipendenteAttraverso(Integer idDipendente) {

    return(Dipendente) jdbcTemplate.queryForObject("select * from dipendente where idDipendente=?",
        new Object[]{idDipendente},
        new BeanPropertyRowMapper(Dipendente.class)
        );
  }


}
