package com.roberto.persistor;

import com.roberto.entitys.PolizzaProva;
import org.springframework.jdbc.core.JdbcTemplate;

public class PolizzaProvaPersistor {


  public static int inserisciPolizzaProva(JdbcTemplate jdbcTemplate,PolizzaProva polizzaProva) {

    return jdbcTemplate.update(
        "INSERT INTO polizzaProva (numeroPolizza,"
            + "idContraente,"
            + "idAssicurato,"
            + "idBeneficiario) VALUES (?, ?, ?, ?)",
        polizzaProva.getNumeroPolizza(),
        polizzaProva.getIdContraente(),
        polizzaProva.getIdAssicurato(),
        polizzaProva.getIdBeneficiario());
  }
}
