package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Operazioni {

  private Integer idOperazione;
  private Integer numeroPolizzaInteressata;
  private Integer idAnagraficaInteressata;

}
