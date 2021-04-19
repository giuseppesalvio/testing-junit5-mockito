package com.salvio.entitys;

import java.math.BigDecimal;
import java.util.UUID;

public class OggettoOrdine {

  private UUID idProdotto;
  private BigDecimal prezzoProdotto;

  public OggettoOrdine(Prodotto prodotto){
    this.idProdotto=prodotto.getId();
    this.prezzoProdotto=prodotto.getPrezzo();

  }

  public UUID getIdProdotto(){

    return idProdotto;
  }

  public BigDecimal getPrezzoProdotto(){

    return prezzoProdotto;
  }




}
