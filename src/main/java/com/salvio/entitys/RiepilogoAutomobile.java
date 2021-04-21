package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RiepilogoAutomobile {

  private String numeroTarga;
  private String codiceFiscaleProprietario;
  private String codiceAssicurazione;
  private Integer numeroPolizzaAssociata;


}
