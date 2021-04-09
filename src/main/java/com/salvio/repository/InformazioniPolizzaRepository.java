package com.salvio.repository;

import com.salvio.entitys.PolizzaProva;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformazioniPolizzaRepository {

  private final JdbcTemplate jdbcTemplate;

  public InformazioniPolizzaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public List<PolizzaProva> getListaPolizzeAssociateAIdAnagrafica(int idAnagrafica) {

    String sql="select * from PolizzaProva where idContraente=? or idAssicurato=? or idBeneficiario=?";
    return jdbcTemplate.query(sql, new Object[]{idAnagrafica,idAnagrafica,idAnagrafica},
        (rs, rowNum) ->
        new PolizzaProva(
            rs.getInt("numeroPolizza"),
            rs.getInt("idContraente"),
            rs.getInt("idAssicurato"),
            rs.getInt("idBeneficiario")
        )
    );

  }
}
