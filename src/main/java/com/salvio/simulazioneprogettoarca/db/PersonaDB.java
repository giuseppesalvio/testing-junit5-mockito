package com.salvio.simulazioneprogettoarca.db;

import lombok.Value;

@Value
public class PersonaDB {

  Integer id;
  String nome;
  String cognome;
  String codiceFiscale;
  Integer indirizzo;

}
