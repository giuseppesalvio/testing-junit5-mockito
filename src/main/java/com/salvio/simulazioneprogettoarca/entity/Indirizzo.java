package com.salvio.simulazioneprogettoarca.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Indirizzo {

  String via;
  Integer numeroCivico;
  String citta;
  String provincia;
  Integer cap;
}
