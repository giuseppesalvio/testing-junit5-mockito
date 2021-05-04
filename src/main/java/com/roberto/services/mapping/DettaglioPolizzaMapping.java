package com.roberto.services.mapping;

import com.roberto.entitys.AnagraficaProva;
import com.roberto.entitys.DettaglioPolizzaProva;
import com.roberto.entitys.PolizzaProva;
import com.roberto.repository.InformazioniAnagraficaRepository;
import org.springframework.stereotype.Component;

@Component
public class DettaglioPolizzaMapping {

    private final InformazioniAnagraficaRepository informazioniAnagraficaRepository;

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
