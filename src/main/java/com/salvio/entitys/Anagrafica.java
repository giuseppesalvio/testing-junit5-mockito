package com.core.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Anagrafica {
  private Integer id;
  private String nome;
  private String cognome;
  private String codiceFiscale;
}
