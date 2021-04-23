package com.salvio.Persistor;

import com.salvio.entitys.ProdottoInfoBaseProva;
import java.util.List;

public class ProdottiSupermercatoProvaPersistor {

  public static void aggiungiProdottoInfoBaseProvaALista(List<ProdottoInfoBaseProva> lista,String codice,String nome,Double costo) {

    lista.add(ProdottoInfoBaseProva.builder()
        .codiceProdotto(codice)
        .nomeProdotto(nome)
        .costoProdotto(costo)
        .build());
  }

}
