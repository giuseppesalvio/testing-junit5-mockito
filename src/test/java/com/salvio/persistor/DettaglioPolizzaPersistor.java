package com.salvio.persistor;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Polizza;

import java.util.ArrayList;
import java.util.List;

public class DettaglioPolizzaPersistor {
    public static List<DettaglioPolizza> getListaDettaglioPolizze(String codiceFiscale) {
        List<DettaglioPolizza> dettaglioPolizzaList = new ArrayList<>();
        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(1, 9999, 9999, 9999),
                new Anagrafica(9999, "Mario", "Rossi", codiceFiscale),
                new Anagrafica(9999, "Mario", "Rossi", codiceFiscale),
                new Anagrafica(9999, "Mario", "Rossi", codiceFiscale)
        ));

        dettaglioPolizzaList.add(new DettaglioPolizza(
                new Polizza(2, 9999, 0000, 2222),
                new Anagrafica(9999, "Mario", "Rossi", codiceFiscale),
                new Anagrafica(0000, "Gennaro", "Esposito", "123123"),
                new Anagrafica(2222, "Ciccio", "Pluto", "3333333")
        ));
        return dettaglioPolizzaList;
    }
}
