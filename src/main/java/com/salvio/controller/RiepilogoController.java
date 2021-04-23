package com.salvio.controller;

import com.salvio.entitys.InfoPolizzaCompletaRiepilogo;
import com.salvio.services.RiepilogoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
