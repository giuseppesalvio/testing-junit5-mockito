package com.salvio.controller;

import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.services.InformazioniPolizzaService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformazioniPolizzaController {

  private final InformazioniPolizzaService informazioniPolizzaService;

  public InformazioniPolizzaController(InformazioniPolizzaService informazioniPolizzaService) {
    this.informazioniPolizzaService = informazioniPolizzaService;
  }

  @PostMapping("//get-informazioni-dettaglio-polizza")
  public List<DettaglioPolizzaProva> gestisciEndpoint(@RequestBody String codiceFiscale) {

      return informazioniPolizzaService.estraiEProcessaInformazioni(codiceFiscale);
  }
}
