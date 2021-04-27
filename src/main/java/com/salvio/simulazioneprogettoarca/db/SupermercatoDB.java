package com.salvio.simulazioneprogettoarca.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SupermercatoDB {

  private Integer idSupermercato;
  private String nomeSupermercato;
  private String via;
  private String citta;
  private String provincia;



}
