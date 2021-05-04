package com.roberto.persistor;

import com.roberto.entitys.Anagrafica;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnagraficaPersistorNew {

  public static void inserisciAnagrafica(JdbcTemplate jdbcTemplate, int id, String nome, String cognome, String codiceFiscale) {
    jdbcTemplate.update(
        "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
        id,
        nome,
        cognome,
        codiceFiscale);

  }

  public static List<Anagrafica> getAnagrafiche(){

    List<Anagrafica> lista= new ArrayList<>();
      lista.add(new Anagrafica(9999,"mario","rossi","1234567890123456"));
      lista.add(new Anagrafica(9999,"mario","rossi","1234567890123456"));
      lista.add(new Anagrafica(9999,"mario","rossi","1234567890123456"));




    return lista;
  }
}
