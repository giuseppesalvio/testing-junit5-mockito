package com.salvio.services;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.InformazioniAnagraficaRepository;
import com.salvio.repository.InformazioniPolizzaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InformazioniPolizzaService {

  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;
  private InformazioniPolizzaRepository informazioniPolizzaRepository;

  public InformazioniPolizzaService(InformazioniAnagraficaRepository informazioniAnagraficaRepository,
      InformazioniPolizzaRepository informazioniPolizzaRepository) {
    this.informazioniAnagraficaRepository = informazioniAnagraficaRepository;
    this.informazioniPolizzaRepository = informazioniPolizzaRepository;
  }

  public List<DettaglioPolizzaProva> estraiEProcessaInformazioni(String codiceFiscale) {

    AnagraficaProva anagraficaProva=informazioniAnagraficaRepository.getAnagraficaDaCodiceFiscale(codiceFiscale);
    List<PolizzaProva> listaPolizzeAssociateCodiceFiscale= informazioniPolizzaRepository.getListaPolizzeAssociateAIdAnagrafica(anagraficaProva.getIdAnagrafica());

    AnagraficaProva anagraficaContraente= null;
    AnagraficaProva anagraficaAssicurato=null;
    AnagraficaProva anagraficaBeneficiario=null;
    DettaglioPolizzaProva dettaglioPolizzaProva=null;
    // le 4 variabili sopra le avevo dichiarate dentro al for, tanto non mi servono, ma forse non era corretto

    List<DettaglioPolizzaProva> listDettaglioPolizzaProva=new ArrayList<>();


    for (PolizzaProva polizzaProva: listaPolizzeAssociateCodiceFiscale)
    {
      anagraficaContraente =informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(polizzaProva.getIdContraente());
      anagraficaAssicurato =informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(polizzaProva.getIdAssicurato());
      anagraficaBeneficiario =informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(polizzaProva.getIdBeneficiario());
      dettaglioPolizzaProva=new DettaglioPolizzaProva(polizzaProva,anagraficaContraente,anagraficaAssicurato,anagraficaBeneficiario);
      listDettaglioPolizzaProva.add(dettaglioPolizzaProva);
    }



    return listDettaglioPolizzaProva;
  }
}
