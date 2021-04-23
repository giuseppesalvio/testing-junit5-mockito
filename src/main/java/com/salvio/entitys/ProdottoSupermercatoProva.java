package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdottoSupermercatoProva {

  private String codiceProdotto;
  private String nomeProdotto;
  private Double costoProdotto;
  private String provenienzaProdotto;
  private String scadenzaProdotto;
  private String categoriaProdotto;

}
