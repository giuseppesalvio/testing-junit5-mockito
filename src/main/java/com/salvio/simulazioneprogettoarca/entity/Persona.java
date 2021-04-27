package com.salvio.simulazioneprogettoarca.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Persona {

  String nome;
  String cognome;
  String codiceFiscale;

}
