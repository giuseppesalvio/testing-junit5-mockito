package com.arca.entity;

import lombok.Value;

@Value
public class ConteggioAccessi {

  private String data;
  private Integer accessiUnivociGG;
  private Integer accessiNonUnivociGG;

}
