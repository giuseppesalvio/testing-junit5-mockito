package com.salvio.repository;

import com.salvio.entitys.Polizza;
import com.salvio.entitys.PolizzaProva;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OperazioniRepository {

  private final JdbcTemplate jdbcTemplate;

  public OperazioniRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public PolizzaProva getPolizzaByNumeroPolizza(int numeroPolizzaRichiesta) {

    return jdbcTemplate.queryForObject("SELECT * FROM PolizzaProva WHERE numeroPolizza=?",
        new Object[]{numeroPolizzaRichiesta},
        (rs, rowNum) ->
            new PolizzaProva(
                rs.getInt("numeroPolizza"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario"))
                );
  }


}
