package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ProdottoInfoBase {

  private String codiceProdotto;
  private String nomeProdotto;
  private Double costoProdotto;
}
