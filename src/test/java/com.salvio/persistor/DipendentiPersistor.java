package com.salvio.persistor;

import org.springframework.jdbc.core.JdbcTemplate;

public class DipendentiPersistor {

  public static void inserisciDipendente(JdbcTemplate jdbcTemplate, Integer idDipendente, String codiceAzienda, String ruolo,
      String dataAssunzione, Double stipendio) {

    jdbcTemplate.update("insert into Dipendente(idDipendente,codiceAzienda,ruolo,dataAssunzione,stipendio) values (?,?,?,?,?)",
        idDipendente,
        codiceAzienda,
        ruolo,
        dataAssunzione,
        stipendio
    );

  }


}
