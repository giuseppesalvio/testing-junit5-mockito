package com.salvio.services;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Polizza;
import com.salvio.repository.AnagraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RicercaAnagraficheDiPolizzaService {

    @Autowired
    private final AnagraficaRepository anagraficaRepository;


    /*
    @Autowired
    private final PolizzaRepository polizzaRepository;
     */

    public RicercaAnagraficheDiPolizzaService(AnagraficaRepository anagraficaRepository) {
        this.anagraficaRepository = anagraficaRepository;
    }

    public List<DettaglioPolizza> execute(String codiceFiscale) {

        Anagrafica anagrafica = anagraficaRepository.ottieniAnagraficaAttraversoCodiceFiscale(codiceFiscale);

        //List<Polizza> listaPolizzeAssociate=polizzaRepository.cercaByCodiceAnagrafica(anagrafica.getId());

        // questo è un commento vero e proprio listaPolizzeAssociate= [{1,9999,9999,9999},{2,9999,8888,2222}]
        //forse si potrebbe fare un for sulla dimensione della listaPolizzeAssociate e usare l'indice del ciclo per prelevare e processare gli elementi della lista

        //List<Anagrafica> anagrafichePrimaPolizza= anagraficaRepository.getListaAnagraficheAssociateAPolizza(listaPolizzeAssociate.get(0));
        //List<Anagrafica> anagraficheSecondaPolizza= anagraficaRepository.getListaAnagraficheAssociateAPolizza(listaPolizzeAssociate.get(1));


        //List <DettaglioPolizza> dettaglioPolizzaList = new ArrayList<>();
        //dettaglioPolizzaList.add(new(listaPolizzeAssociate.get(0),anagrafichePrimaPolizza.get(0),anagrafichePrimaPolizza.get(1),anagrafichePrimaPolizza.get(2)) );

        //dettaglioPolizzaList.add(new(listaPolizzeAssociate.get(1),anagraficheSecondaPolizza.get(0),anagraficheSecondaPolizza.get(1),anagraficheSecondaPolizza.get(2)) );

        //return dettaglioPolizzaList;




        List<DettaglioPolizza> dettaglioPolizzaList = new ArrayList<>();
        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(1, 9999, 9999, 9999),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456")
        ));

        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(2, 9999, 8888, 2222),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(8888, "Gennaro", "Esposito", "123123"),
                new Anagrafica(2222, "Ciccio", "Pluto", "3333333")
        ));
        return dettaglioPolizzaList;
    }
}
