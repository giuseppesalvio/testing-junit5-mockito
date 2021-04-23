package com.salvio.controller;

import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import com.salvio.services.SupermercatoOnlineService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupermercatoOnlineController {

  private final SupermercatoOnlineService supermercatoOnlineService;

  public SupermercatoOnlineController(SupermercatoOnlineService supermercatoOnlineService) {
    this.supermercatoOnlineService = supermercatoOnlineService;
  }

  @PostMapping("/getAllProducts")
  public List<ProdottoInfoBaseProva> getAllProducts() {

    return supermercatoOnlineService.ottieniTutti();

  }
  @PostMapping("/getBy")
  public ProdottoInfoCompletaProva getBy(@RequestBody  String codiceProdotto) {

    return supermercatoOnlineService.ottieniAttraverso(codiceProdotto);

  }
}
