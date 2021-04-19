package com.salvio.services;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.InformazioniAnagraficaRepository;
import com.salvio.repository.InformazioniPolizzaRepository;
import com.salvio.services.mapping.DettaglioPolizzaMapping;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InformazioniPolizzaService {

  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;
  private InformazioniPolizzaRepository informazioniPolizzaRepository;
  private DettaglioPolizzaMapping dettaglioPolizzaMapping;

  public InformazioniPolizzaService(InformazioniAnagraficaRepository informazioniAnagraficaRepository,
      InformazioniPolizzaRepository informazioniPolizzaRepository,
      DettaglioPolizzaMapping dettaglioPolizzaMapping) {
    this.informazioniAnagraficaRepository = informazioniAnagraficaRepository;
    this.informazioniPolizzaRepository = informazioniPolizzaRepository;
    this.dettaglioPolizzaMapping = dettaglioPolizzaMapping;
  }

  public List<DettaglioPolizzaProva> getDettaglioPolizza(String codiceFiscale) {

    AnagraficaProva anagraficaProva = informazioniAnagraficaRepository.getAnagraficaDaCodiceFiscale(codiceFiscale);
    List<PolizzaProva> listaPolizzeAssociateCodiceFiscale = informazioniPolizzaRepository
        .getListaPolizzeAssociateAIdAnagrafica(anagraficaProva.getIdAnagrafica());

    List<DettaglioPolizzaProva> listDettaglioPolizzaProva = new ArrayList<>();

    for (PolizzaProva polizzaProva : listaPolizzeAssociateCodiceFiscale) {
      listDettaglioPolizzaProva.add(dettaglioPolizzaMapping.execute(polizzaProva));
    }


    return listDettaglioPolizzaProva;
  }



}
