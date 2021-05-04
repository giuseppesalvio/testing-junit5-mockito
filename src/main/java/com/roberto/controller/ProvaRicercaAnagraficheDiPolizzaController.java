package com.roberto.controller;

import com.roberto.entitys.ProvaDettaglioPolizza;
import com.roberto.services.ProvaRicercaAnagraficheDiPolizzaService;

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