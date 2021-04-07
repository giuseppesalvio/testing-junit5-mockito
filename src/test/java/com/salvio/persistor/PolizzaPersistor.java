package com.salvio.persistor;

import org.springframework.jdbc.core.JdbcTemplate;

public class PolizzaPersistor {

  public static void inserisciPolizza(JdbcTemplate jdbcTemplate,int id, int idContraente, int idAssicurato, int idBeneficiario) {
    jdbcTemplate.update(
        "INSERT INTO polizza (id, idContraente,idAssicurato,idBeneficiario) VALUES (?, ?, ?, ?)",
        id,
        idContraente,
        idAssicurato,
        idBeneficiario);
  }
}
