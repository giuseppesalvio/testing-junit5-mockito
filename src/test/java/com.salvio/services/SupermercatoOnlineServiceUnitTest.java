package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.entitys.InfoMagazzinoSupermercatoProva;
import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import com.salvio.entitys.ProdottoInfoEstesaProva;
import com.salvio.entitys.ProdottoSupermercato;
import com.salvio.entitys.ProdottoSupermercatoProva;
import com.salvio.repository.SupermercatoOnlineMagazzinoRepository;
import com.salvio.repository.SupermercatoOnlineProdottoRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupermercatoOnlineServiceUnitTest {


  @InjectMocks
  private SupermercatoOnlineService supermercatoOnlineService;

  @Mock
  private SupermercatoOnlineProdottoRepository supermercatoOnlineProdottoRepository;

  @Mock
  private SupermercatoOnlineMagazzinoRepository supermercatoOnlineMagazzinoRepository;

  @Test
  public void funzionamentoServiceGetAllOk() {

    List<ProdottoSupermercatoProva> listaMock = new ArrayList<>();
    inserisciProdottoSupermercatoALista(listaMock, "P0001", "farina", 0.35, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0002", "latte", 0.60, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0003", "pasta", 0.55, "ITALIA", "2022-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0004", "acqua", 0.25, "ITALIA", "2025-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0005", "pentola", 5.00, "GERMANIA", null, "cucina");

    when(supermercatoOnlineProdottoRepository.estraiTutti()).thenReturn(listaMock);

    List<ProdottoInfoBaseProva> listaOttenuta = supermercatoOnlineService.ottieniTutti();

    Assertions.assertThat(listaOttenuta.size()).isEqualTo(5);
  }

  @Test
  public void funzionamentoServiceGetByOk() {
    String codiceProdotto = "P0001";

    ProdottoSupermercatoProva prodottoMock = ProdottoSupermercatoProva.builder().codiceProdotto("P0001").nomeProdotto("farina")
        .costoProdotto(0.35).categoriaProdotto("alimentare").provenienzaProdotto("ITALIA").scadenzaProdotto("2021-12-31").build();
    InfoMagazzinoSupermercatoProva infoMock = InfoMagazzinoSupermercatoProva.builder().codiceProdotto("P0001").disponibilita(100)
        .build();

    when(supermercatoOnlineProdottoRepository.estraiTramite(codiceProdotto)).thenReturn(prodottoMock);
    when(supermercatoOnlineMagazzinoRepository.estraiDisponibilita(codiceProdotto)).thenReturn(infoMock);

    ProdottoInfoCompletaProva prodottoAssociato = supermercatoOnlineService.ottieniAttraverso(codiceProdotto);

    Assertions.assertThat(prodottoAssociato.getDisponibilita()).isEqualTo(100);


  }

  @Test
  public void funzionamentoServiceGetOrdinatiPer() {
    String parametroOrdinamento="costoProdotto";

    List<ProdottoSupermercatoProva> listaMock = new ArrayList<>();
    inserisciProdottoSupermercatoALista(listaMock, "P0004", "acqua", 0.25, "ITALIA", "2025-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0001", "farina", 0.35, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0003", "pasta", 0.55, "ITALIA", "2022-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0002", "latte", 0.60, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0005", "pentola", 5.00, "GERMANIA", null, "cucina");

    when(supermercatoOnlineProdottoRepository.estraiOrdinatiPer(parametroOrdinamento)).thenReturn(listaMock);

    List<ProdottoInfoBaseProva> listaOrdinata =  supermercatoOnlineService.ottieniOrdinatiPer(parametroOrdinamento);

    Assertions.assertThat(listaOrdinata.get(0).getNomeProdotto()).isEqualTo("acqua");

  }

  @Test
  public void funzionamentoServiceGetFiltratiPer() {

    String categoriaRichiesta="alimentare";

    List<ProdottoSupermercatoProva> listaMock = new ArrayList<>();
    inserisciProdottoSupermercatoALista(listaMock, "P0001", "farina", 0.35, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0002", "latte", 0.60, "ITALIA", "2021-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0003", "pasta", 0.55, "ITALIA", "2022-12-31", "alimentare");
    inserisciProdottoSupermercatoALista(listaMock, "P0004", "acqua", 0.25, "ITALIA", "2025-12-31", "alimentare");

    when(supermercatoOnlineProdottoRepository.estraiFiltratiPer(categoriaRichiesta)).thenReturn(listaMock);

    List<ProdottoInfoEstesaProva> listaFiltrata= supermercatoOnlineService.ottieniFiltratiPer(categoriaRichiesta);


    Assertions.assertThat(listaFiltrata.size()).isEqualTo(4);
  }



  public void inserisciProdottoSupermercatoALista(List<ProdottoSupermercatoProva> lista, String codice, String nome, Double costo,
      String provenienza, String scadenza, String categoria) {

    lista.add(ProdottoSupermercatoProva.builder()
        .codiceProdotto(codice)
        .nomeProdotto(nome)
        .costoProdotto(costo)
        .provenienzaProdotto(provenienza)
        .scadenzaProdotto(scadenza)
        .categoriaProdotto(categoria)
        .build()
    );

  }


}
