package com.salvio.services.mapping;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.InformazioniAnagraficaRepository;
import org.springframework.stereotype.Component;

@Component
public class DettaglioPolizzaMapping {

  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;

  public DettaglioPolizzaMapping(InformazioniAnagraficaRepository informazioniAnagraficaRepository) {
    this.informazioniAnagraficaRepository = informazioniAnagraficaRepository;
  }


  public DettaglioPolizzaProva execute(PolizzaProva polizzaProva) {

    AnagraficaProva anagraficaContraente = informazioniAnagraficaRepository
        .getAnagraficheDaIdAnagrafica(polizzaProva.getIdContraente());
    AnagraficaProva anagraficaAssicurato = informazioniAnagraficaRepository
        .getAnagraficheDaIdAnagrafica(polizzaProva.getIdAssicurato());
    AnagraficaProva anagraficaBeneficiario = informazioniAnagraficaRepository
        .getAnagraficheDaIdAnagrafica(polizzaProva.getIdBeneficiario());
    DettaglioPolizzaProva dettaglioPolizzaProva = new DettaglioPolizzaProva(polizzaProva, anagraficaContraente,
        anagraficaAssicurato, anagraficaBeneficiario);
    return dettaglioPolizzaProva;
  }
}
