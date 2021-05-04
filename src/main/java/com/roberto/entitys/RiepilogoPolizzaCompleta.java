package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RiepilogoPolizzaCompleta {


  private Integer numeroPolizza;
  private Integer idContraente;
  private Integer idAssicurato;
  private Integer idBeneficiario;
  private String dataProxQuitanzamento;
  private Double importoQuietanzamento;

}
