package com.salvio.repository;

import com.salvio.entitys.RiepilogoPolizzaCompleta;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RiepilogoPolizzaRepository {

  private final JdbcTemplate jdbcTemplate;

  public RiepilogoPolizzaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<RiepilogoPolizzaCompleta> getInfoPolizzeBy(Integer idAnagrafica) {

    String sql = "SELECT * FROM riepilogoPolizza where idContraente=? or idAssicurato=? or idBeneficiario=? ";

    return jdbcTemplate.query(
        sql, new Object[]{idAnagrafica,idAnagrafica,idAnagrafica},
        (rs, rowNum) ->
            new RiepilogoPolizzaCompleta(
                rs.getInt("numeroPolizza"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario"),
                rs.getString("dataProxQuietanzamento"),
                rs.getDouble("importoQuietanzamento")
            )
    );
  }

}

