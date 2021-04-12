package com.salvio.controller;

import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.services.ValiditàSinistroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValiditaSinistroController {

  private ValiditàSinistroService validitàSinistroService;

  public ValiditaSinistroController(ValiditàSinistroService validitàSinistroService) {
    this.validitàSinistroService = validitàSinistroService;
  }


  @PostMapping("/verifica-compatibilità-sinistro-and-calcolo-premio-dovuto")
  public InfoPolizzaEstesa gestisciEndPoint(@RequestBody String infoSinistro) {

    return validitàSinistroService.executeOperazioniService(infoSinistro);
  }
}
