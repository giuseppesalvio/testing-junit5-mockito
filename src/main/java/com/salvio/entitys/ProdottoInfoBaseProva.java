package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdottoInfoBaseProva {


  private String codiceProdotto;
  private String nomeProdotto;
  private Double costoProdotto;

}
