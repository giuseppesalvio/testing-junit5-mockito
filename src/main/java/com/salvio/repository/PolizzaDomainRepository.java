package com.salvio.repository;

import com.salvio.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PolizzaDomainRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;


  public List<PolizzaDomain> cercaByCodiceAnagrafica(Integer idAnagrafica) {

   return jdbcTemplate.query("select * from polizzaDomain where idContraente=? or idAssicurato = ? or idBeneficiario=?",
        new Object[]{idAnagrafica, idAnagrafica, idAnagrafica},
        (rs, rowNum) ->
            new PolizzaDomain(
                rs.getInt("numeroPolizza"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario")));

  }

}
