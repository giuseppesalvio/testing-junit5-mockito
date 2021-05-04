package com.roberto.controller;

import com.roberto.entitys.InfoPolizzaCompletaRiepilogo;
import com.roberto.services.RiepilogoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiepilogoController {

  private final RiepilogoService riepilogoService;

  public RiepilogoController(RiepilogoService riepilogoService) {
    this.riepilogoService = riepilogoService;
  }

  @PostMapping("/getRiepilogo")
  public InfoPolizzaCompletaRiepilogo checkAndReturnLinkedList(@RequestBody String infoSinistro) {

    return riepilogoService.costruisciRispostaService(infoSinistro);



  }
}
