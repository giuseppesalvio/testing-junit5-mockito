package com.salvio.controller;

import com.google.gson.Gson;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.Operazioni;
import com.salvio.entitys.PolizzaProva;
import com.salvio.services.InformazioniPolizzaService;
import com.salvio.services.OperazioniService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformazioniPolizzaController {

  private final InformazioniPolizzaService informazioniPolizzaService;
  private final OperazioniService operazioniService;


  public InformazioniPolizzaController(InformazioniPolizzaService informazioniPolizzaService, OperazioniService operazioniService) {
    this.informazioniPolizzaService = informazioniPolizzaService;
    this.operazioniService = operazioniService;


  }



  @PostMapping("/get-informazioni-dettaglio-polizza")
  public List<DettaglioPolizzaProva> gestisciEndpoint(@RequestBody String codiceFiscale) {

      return informazioniPolizzaService.getDettaglioPolizza(codiceFiscale);
  }




  //controller nuove funzionalità

  @PostMapping("/get-operazioni-su-polizza-richieste-da-id-anagrafica")
  public PolizzaProva gestisciEndPointPolizze(@RequestBody String jsonRequestPerOperazioniPolizza){

    Gson gson = new Gson();
    Operazioni op= gson.fromJson(jsonRequestPerOperazioniPolizza, Operazioni.class);

    return operazioniService.mostraPolizzaProvaRichiesta(op.getNumeroPolizzaInteressata());
  }



}
