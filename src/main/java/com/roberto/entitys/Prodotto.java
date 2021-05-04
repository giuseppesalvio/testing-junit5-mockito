package com.roberto.entitys;

import java.math.BigDecimal;
import java.util.UUID;

public class Prodotto {

  private UUID id;

  private BigDecimal prezzo;

  public Prodotto(UUID id, BigDecimal prezzo){

    this.id =id;
    this.prezzo=prezzo;


  }

  public UUID getId(){
    return this.id;
  }

  public BigDecimal getPrezzo(){

    return this.prezzo;
  }



}
