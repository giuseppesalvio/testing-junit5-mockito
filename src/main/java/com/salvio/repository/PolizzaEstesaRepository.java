package com.salvio.repository;

import com.salvio.entitys.Automobile;
import com.salvio.entitys.PolizzaEstesa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PolizzaEstesaRepository {

  private JdbcTemplate jdbcTemplate;

  public PolizzaEstesaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public PolizzaEstesa estraiDatiPolizzaFornita(Integer numeroPolizzaAssociata) {

    return(PolizzaEstesa) jdbcTemplate.queryForObject("select * from polizzaEstesa where numeroPolizza=?",

        new Object[]{numeroPolizzaAssociata},
        (rs, rowNum) ->
            new PolizzaEstesa(
                rs.getInt("numeroPolizza"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario"),
                rs.getString("dataProxQuietanzamento"),
                rs.getDouble("importoQuietanzamento")

            ));
  }

  public List<PolizzaEstesa> ottieniPolizzeConContraenteIdFornito(Integer idAnagrafica) {


    return jdbcTemplate.query("select * from polizzaEstesa where idContraente=?",

        new Object[]{idAnagrafica},
        (rs, rowNum) ->
            new PolizzaEstesa(
                rs.getInt("numeroPolizza"),
                rs.getInt("idContraente"),
                rs.getInt("idAssicurato"),
                rs.getInt("idBeneficiario"),
                rs.getString("dataProxQuietanzamento"),
                rs.getDouble("importoQuietanzamento")

            ));

  }

}
