package com.roberto.controller;

import com.roberto.entitys.InfoPolizzaEstesa;
import com.roberto.services.ValiditàSinistroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValiditaSinistroController {

  private final ValiditàSinistroService validitàSinistroService;

  public ValiditaSinistroController(ValiditàSinistroService validitàSinistroService) {
    this.validitàSinistroService = validitàSinistroService;
  }


  @PostMapping("/verifica-compatibilita-sinistro-and-calcolo-premio-dovuto")
  public InfoPolizzaEstesa gestisciEndPoint(@RequestBody String infoSinistro) {

    return validitàSinistroService.executeOperazioniService(infoSinistro);
  }
}
