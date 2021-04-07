package com.salvio.services;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.entitys.ProvaDettaglioPolizza;
import com.salvio.repository.AnagraficaRepositoryProva;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProvaRicercaAnagraficheDiPolizzaService {

  private final AnagraficaRepositoryProva anagraficaRepositoryProva;

  public ProvaRicercaAnagraficheDiPolizzaService(AnagraficaRepositoryProva anagraficaRepositoryProva) {
    this.anagraficaRepositoryProva = anagraficaRepositoryProva;
  }

  public List<ProvaDettaglioPolizza> ottieniPolizzeCollegateACodiceFiscaleFornito(String codiceFiscale) {

    AnagraficaProva anagraficaProva = anagraficaRepositoryProva.getAnagraficaDaCodiceFiscale(codiceFiscale);


    List<ProvaDettaglioPolizza> dettaglioPolizzaList= new ArrayList<>();

    dettaglioPolizzaList.add(new ProvaDettaglioPolizza(
        new PolizzaProva(1,9999,9999,9999),
        new AnagraficaProva(9999,"Mario","Rossi","1234567890123456"),
        new AnagraficaProva(9999,"Mario","Rossi","1234567890123456"),
        new AnagraficaProva(9999,"Mario","Rossi","1234567890123456")
    ));

    dettaglioPolizzaList.add(new ProvaDettaglioPolizza(
        new PolizzaProva(2,9999,2222,8888),
        new AnagraficaProva(9999,"Mario","Rossi","1234567890123456"),
        new AnagraficaProva(2222,"Gennaro","Esposito","gnnsps13d14i445v"),
        new AnagraficaProva(8888,"Pippo","Pluto","pppplt13d14i445v")

    ));

    return dettaglioPolizzaList;

  }
}
