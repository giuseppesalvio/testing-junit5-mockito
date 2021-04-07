package com.salvio.controller;

import com.salvio.entitys.PolizzaCollegata;
import com.salvio.services.PolizzaCollegataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PolizzaCollegataController {

  private PolizzaCollegataService polizzaCollegataService;

  public PolizzaCollegataController(PolizzaCollegataService polizzaCollegataService) {
    this.polizzaCollegataService = polizzaCollegataService;
  }

  @GetMapping("/getListaPolizzeAssociateAlCfInserito")
  public List<PolizzaCollegata> getPolizzeCollegateDaController(
      @RequestParam(value = "codiceFiscale") String codiceFiscale) {

    return polizzaCollegataService.getPolizzaCollegataDaService(codiceFiscale);

  }
}
