package com.salvio.entitys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Ordine {


  private UUID idOrdine;
  private List<OggettoOrdine> listaOggetti;
  private StatoOrdine statoOrdine;
  private BigDecimal prezzo;

  public Ordine(UUID id, Prodotto prodotto) {

    this.idOrdine = id;
    this.listaOggetti = new ArrayList<>(Arrays.asList(new OggettoOrdine(prodotto)));
    this.statoOrdine = this.statoOrdine.CREATO;
    this.prezzo = prodotto.getPrezzo();
  }

  public void ordineCompletato() {
    validaStato();
    statoOrdine = statoOrdine.COMPLETATO;
  }

  private boolean validaStato() {
    switch (statoOrdine) {

      case CREATO:
        System.out.println("verifica effettuata, aggiungere altro articolo");
        return false;

      case COMPLETATO:
        System.out.println("ordine gia completato, impossibile aggiungere altro");
        return true;

      default:
        return true;
    }
  }

  public void aggiungiOrdine(Prodotto prodotto) {

    if (!validaStato()) {
      validaProdotto(prodotto);
      listaOggetti.add(new OggettoOrdine(prodotto));
      prezzo = prezzo.add(prodotto.getPrezzo());
    }
  }

  public void rimuoviOrdine(UUID idOrdine) {

    validaStato();
    OggettoOrdine oggetto = ottieniOggettoDaId(idOrdine);
    prezzo = prezzo.subtract(oggetto.getPrezzoProdotto());
    listaOggetti.remove(oggetto);
  }

  private OggettoOrdine ottieniOggettoDaId(UUID idOrdine) {

    OggettoOrdine oggetto = new OggettoOrdine(new Prodotto(idOrdine, new BigDecimal(500.00)));
    return oggetto;
  }

  private void validaProdotto(Prodotto prodotto) {

  }


}
