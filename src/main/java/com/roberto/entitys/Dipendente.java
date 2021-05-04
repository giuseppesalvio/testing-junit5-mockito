package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Dipendente {

  private Integer idDipendente;
  private String  codiceAzienda;
  private String  ruolo;
  private String  dataAssunzione;
  private Double  stipendio;


}
