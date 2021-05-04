package com.roberto.services;

import com.roberto.entitys.AnagraficaProva;
import com.roberto.entitys.DettaglioPolizzaProva;
import com.roberto.entitys.PolizzaProva;
import com.roberto.repository.InformazioniAnagraficaRepository;
import com.roberto.repository.InformazioniPolizzaRepository;
import com.roberto.services.mapping.DettaglioPolizzaMapping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformazioniPolizzaService {

    private final InformazioniAnagraficaRepository informazioniAnagraficaRepository;
    private final InformazioniPolizzaRepository informazioniPolizzaRepository;
    private final DettaglioPolizzaMapping dettaglioPolizzaMapping;

    public InformazioniPolizzaService(InformazioniAnagraficaRepository informazioniAnagraficaRepository,
                                      InformazioniPolizzaRepository informazioniPolizzaRepository) {
        this.informazioniAnagraficaRepository = informazioniAnagraficaRepository;
        this.informazioniPolizzaRepository = informazioniPolizzaRepository;
        this.dettaglioPolizzaMapping = new DettaglioPolizzaMapping(this.informazioniAnagraficaRepository);
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
