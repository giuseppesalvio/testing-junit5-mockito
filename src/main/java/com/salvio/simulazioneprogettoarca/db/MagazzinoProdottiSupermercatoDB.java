package com.salvio.simulazioneprogettoarca.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MagazzinoProdottiSupermercatoDB {
  private Integer idSupermercato;
  private Integer codiceProdotto;
  private String nomeProdotto;
  private Double costoUnitario;
  private Integer disponibilita;


}
