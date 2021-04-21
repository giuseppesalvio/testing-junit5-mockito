package com.salvio.controller;

import com.salvio.Response.AziendaAssociataResponse;
import com.salvio.Response.DipendentiAziendaResponse;
import com.salvio.services.GestioneAziendeService;
import com.salvio.services.GestioneDipendentiService;
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
