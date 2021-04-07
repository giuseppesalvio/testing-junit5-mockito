package com.salvio.services;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Polizza;
import com.salvio.repository.AnagraficaRepository;
import com.salvio.repository.PolizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RicercaAnagraficheDiPolizzaService {

    @Autowired
    private final AnagraficaRepository anagraficaRepository;

    @Autowired
    private final PolizzaRepository polizzaRepository;

    public RicercaAnagraficheDiPolizzaService(AnagraficaRepository anagraficaRepository,PolizzaRepository polizzaRepository) {
        this.anagraficaRepository = anagraficaRepository;
        this.polizzaRepository = polizzaRepository;
    }


    public List<DettaglioPolizza> execute(String codiceFiscale) {

        Anagrafica anagrafica = anagraficaRepository.ottieniAnagraficaAttraversoCodiceFiscale(codiceFiscale);

        List<Polizza> listaPolizzeAssociate =polizzaRepository.cercaByCodiceAnagrafica(anagrafica.getId());

        // questo è un commento vero e proprio listaPolizzeAssociate= [{1,9999,9999,9999},{2,9999,8888,2222}]
        //eventualmente ciclo sulla lunghezza della lista

        List<Anagrafica> anagrafichePrimaPolizza= anagraficaRepository.getListaAnagraficheAssociateAPolizza(listaPolizzeAssociate.get(0));

        List <DettaglioPolizza> dettaglioPolizzaLista = new ArrayList<>();
        dettaglioPolizzaLista.add(new DettaglioPolizza(listaPolizzeAssociate.get(0),anagrafichePrimaPolizza.get(0),anagrafichePrimaPolizza.get(1),anagrafichePrimaPolizza.get(2)) );


        //caso 2 polizze associate ad idAnagrafica
       // List<Anagrafica> anagraficheSecondaPolizza= anagraficaRepository.getListaAnagraficheAssociateAPolizza(listaPolizzeAssociate.get(1));
     // dettaglioPolizzaLista.add(new DettaglioPolizza(listaPolizzeAssociate.get(1),anagraficheSecondaPolizza.get(0),anagraficheSecondaPolizza.get(1),anagraficheSecondaPolizza.get(2)) );



        return dettaglioPolizzaLista;

    }
}
