package com.arca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConteggioAccessiBS {

  private String data;
  private Integer accessiUnivociGG;
  private Integer accessiNonUnivociGG;


}
