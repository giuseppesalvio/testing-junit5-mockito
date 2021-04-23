package com.salvio.services;

import static com.salvio.Persistor.ProdottiSupermercatoProvaPersistor.aggiungiProdottoInfoBaseProvaALista;

import com.salvio.entitys.InfoMagazzinoSupermercatoProva;
import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import com.salvio.entitys.ProdottoSupermercato;
import com.salvio.entitys.ProdottoSupermercatoProva;
import com.salvio.repository.SupermercatoOnlineMagazzinoRepository;
import com.salvio.repository.SupermercatoOnlineProdottoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SupermercatoOnlineService {

  private final SupermercatoOnlineProdottoRepository supermercatoOnlineProdottoRepository;
  private final SupermercatoOnlineMagazzinoRepository supermercatoOnlineMagazzinoRepository;

  public SupermercatoOnlineService(
      SupermercatoOnlineProdottoRepository supermercatoOnlineProdottoRepository,
      SupermercatoOnlineMagazzinoRepository supermercatoOnlineMagazzinoRepository) {
    this.supermercatoOnlineProdottoRepository = supermercatoOnlineProdottoRepository;
    this.supermercatoOnlineMagazzinoRepository = supermercatoOnlineMagazzinoRepository;
  }




  public List<ProdottoInfoBaseProva> ottieniTutti() {

    List<ProdottoSupermercatoProva> listaTotale = supermercatoOnlineProdottoRepository.estraiTutti();
    List<ProdottoInfoBaseProva> listaDaRestituire = new ArrayList<>();

      for (ProdottoSupermercatoProva prodotto : listaTotale) {

                aggiungiProdottoInfoBaseProvaALista(listaDaRestituire, prodotto.getCodiceProdotto(), prodotto.getNomeProdotto(),
                    prodotto.getCostoProdotto());

      }

      return listaDaRestituire;
  }




  public ProdottoInfoCompletaProva ottieniAttraverso(String codiceProdotto) {

        ProdottoSupermercatoProva prodottoSupermercato= supermercatoOnlineProdottoRepository.estraiTramite(codiceProdotto);
        InfoMagazzinoSupermercatoProva infoProva= supermercatoOnlineMagazzinoRepository.estraiDisponibilita(codiceProdotto);

    ProdottoInfoCompletaProva prodotto= ProdottoInfoCompletaProva.builder()
                                                              .codiceProdotto(prodottoSupermercato.getCodiceProdotto())
                                                              .nomeProdotto(prodottoSupermercato.getNomeProdotto())
                                                              .costoProdotto(prodottoSupermercato.getCostoProdotto())
                                                              .categotiaProdotto(prodottoSupermercato.getCategoriaProdotto())
                                                              .provenienza(prodottoSupermercato.getProvenienzaProdotto())
                                                              .scadenza(prodottoSupermercato.getScadenzaProdotto())
                                                              .disponibilita(infoProva.getDisponibilita())
                                                              .build();

        return prodotto;
  }
}
