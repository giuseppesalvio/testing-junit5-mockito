package com.roberto.controller;

import com.google.gson.Gson;
import com.roberto.entitys.DettaglioPolizzaProva;
import com.roberto.entitys.Operazioni;
import com.roberto.entitys.PolizzaProva;
import com.roberto.services.InformazioniPolizzaService;
import com.roberto.services.OperazioniService;
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
