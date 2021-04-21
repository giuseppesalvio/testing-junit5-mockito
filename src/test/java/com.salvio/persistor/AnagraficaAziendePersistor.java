package com.salvio.persistor;

import org.springframework.jdbc.core.JdbcTemplate;

public class AnagraficaAziendePersistor {
  public static void inserisciAnagraficaAzienda(JdbcTemplate jdbcTemplate, int id, String nome, String cognome, String codiceFiscale) {
    jdbcTemplate.update(
        "INSERT INTO anagraficaAziende (idAnagrafica, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
        id,
        nome,
        cognome,
        codiceFiscale);
  }

}
