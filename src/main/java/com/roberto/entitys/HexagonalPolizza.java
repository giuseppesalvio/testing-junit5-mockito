package com.roberto.entitys;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HexagonalPolizza {

  private Integer numeroPolizza;
  private Integer idContraente;
  private Integer idAssicurato;
  private Integer idBeneficiario;
  private Date dataProxQuietanzamento;
  private StatoPolizza statoPolizza;

}
