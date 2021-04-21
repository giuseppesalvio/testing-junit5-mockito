package com.salvio.persistor;

import org.springframework.jdbc.core.JdbcTemplate;

public class AziendaPersistor {

  public static void inserisciAzienda(JdbcTemplate jdbcTemplate, String codiceAzienda, String nomeAzienda,
      String codiceFiscaleProprietarioAzienda, String dataFondazione, String nazione) {

    jdbcTemplate.update(
        "INSERT INTO Azienda (codiceAzienda, nomeAzienda,codiceFiscaleProprietarioAzienda,dataFondazione,nazione) VALUES (?, ?, ?, ?, ?)",
        codiceAzienda,
        nomeAzienda,
        codiceFiscaleProprietarioAzienda,
        dataFondazione,
        nazione
    );
  }
}
