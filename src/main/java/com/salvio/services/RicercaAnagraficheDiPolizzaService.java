package com.salvio.services;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Polizza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RicercaAnagraficheDiPolizzaService {

    public List<DettaglioPolizza> execute(String codiceFiscale) {
        List<DettaglioPolizza> dettaglioPolizzaList = new ArrayList<>();
        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(1, 9999, 9999, 9999),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456")
        ));

        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(2, 9999, 9999, 9999),
                new Anagrafica(9999, "Mario", "Rossi", "1234567890123456"),
                new Anagrafica(0000, "Gennaro", "Esposito", "123123"),
                new Anagrafica(2222, "Ciccio", "Pluto", "3333333")
        ));
        return dettaglioPolizzaList;
    }
}
