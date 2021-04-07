package com.salvio.controller;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.entitys.ProvaDettaglioPolizza;
import com.salvio.services.ProvaRicercaAnagraficheDiPolizzaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvaRicercaAnagraficheDiPolizzaController {

  private final ProvaRicercaAnagraficheDiPolizzaService provaRicercaAnagraficheDiPolizzaService;

  public ProvaRicercaAnagraficheDiPolizzaController(
      ProvaRicercaAnagraficheDiPolizzaService provaRicercaAnagraficheDiPolizzaService) {
    this.provaRicercaAnagraficheDiPolizzaService = provaRicercaAnagraficheDiPolizzaService;
  }


  @PostMapping("/prova-ricerca-anagrafiche-di-polizza")
  public List<ProvaDettaglioPolizza> execute(@RequestBody String codiceFiscale) {

    List<ProvaDettaglioPolizza> lista = provaRicercaAnagraficheDiPolizzaService.ottieniPolizzeCollegateACodiceFiscaleFornito(codiceFiscale);

    return lista;
  }
}