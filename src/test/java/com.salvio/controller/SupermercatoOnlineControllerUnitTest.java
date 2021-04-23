package com.salvio.controller;


import static org.mockito.Mockito.when;

import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import com.salvio.services.SupermercatoOnlineService;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupermercatoOnlineControllerUnitTest {

  @InjectMocks
  private SupermercatoOnlineController supermercatoOnlineController;


  @Mock
  private SupermercatoOnlineService supermercatoOnlineService;

  @Test
  public void funzionamentoControllerGetAllOk(){

    List<ProdottoInfoBaseProva> listaMock=new ArrayList<>();
    aggiungiProdottoInfoBaseProvaALista(listaMock,"P0001","farina",0.35);
    aggiungiProdottoInfoBaseProvaALista(listaMock,"P0002","latte",0.60);
    aggiungiProdottoInfoBaseProvaALista(listaMock,"P0003","pasta",0.55);
    aggiungiProdottoInfoBaseProvaALista(listaMock,"P0004","acqua",0.25);
    aggiungiProdottoInfoBaseProvaALista(listaMock,"P0005","pentola",5.00);


    when(supermercatoOnlineService.ottieniTutti()).thenReturn(listaMock);


    List<ProdottoInfoBaseProva> listaGetAllOttenuta=supermercatoOnlineController.getAllProducts();

    Assertions.assertThat(listaGetAllOttenuta.size()).isEqualTo(5);


  }


  @Test
  public void funzionamentoControllerGetByOk(){

    String codiceProdotto="P0001";
    ProdottoInfoCompletaProva mock= ProdottoInfoCompletaProva.builder().codiceProdotto("P0001").nomeProdotto("farina").build();

    when(supermercatoOnlineService.ottieniAttraverso(codiceProdotto)).thenReturn(mock);

   ProdottoInfoCompletaProva infoCompleteOttenute= supermercatoOnlineController.getBy(codiceProdotto);

   Assertions.assertThat(infoCompleteOttenute.getNomeProdotto()).isEqualTo("farina");


  }

  public void aggiungiProdottoInfoBaseProvaALista(List<ProdottoInfoBaseProva> lista,String codice,String nome,Double costo) {

    lista.add(ProdottoInfoBaseProva.builder()
        .codiceProdotto(codice)
        .nomeProdotto(nome)
        .costoProdotto(costo)
        .build());
  }


}
