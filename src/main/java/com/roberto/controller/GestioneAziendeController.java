package com.roberto.controller;

import com.roberto.response.AziendaAssociataResponse;
import com.roberto.response.DipendentiAziendaResponse;
import com.roberto.services.GestioneAziendeService;
import com.roberto.services.GestioneDipendentiService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestioneAziendeController {

  private GestioneAziendeService gestioneAziendeService;
  private GestioneDipendentiService gestioneDipendentiService;

  public GestioneAziendeController(GestioneAziendeService gestioneAziendeService,
      GestioneDipendentiService gestioneDipendentiService) {
    this.gestioneAziendeService = gestioneAziendeService;
    this.gestioneDipendentiService = gestioneDipendentiService;
  }


  @PostMapping("/getAllDependentFrom")
  public List<DipendentiAziendaResponse> getAllDipendentiFrom(@RequestBody String codiceAzienda) {

      return gestioneAziendeService.getAllBy(codiceAzienda);
  }


  @PostMapping("/getAziendeAssociateA")
  public List<AziendaAssociataResponse>  getAllAziendeAssociate(@RequestBody String codiceFiscaleDipendente){

    return gestioneDipendentiService.getAziendeCorrelate(codiceFiscaleDipendente);
  }


}
