package com.salvio.controller;

import com.salvio.entitys.ProdottoInfoBaseProva;
import com.salvio.entitys.ProdottoInfoCompletaProva;
import com.salvio.entitys.ProdottoInfoEstesaProva;
import com.salvio.services.SupermercatoOnlineService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/supermercato")
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


  @PostMapping("/getAscOrderBy")
  public List<ProdottoInfoBaseProva> getAscOrderBy(@RequestBody String parametroOrdinamento) {
    return supermercatoOnlineService.ottieniOrdinatiPer(parametroOrdinamento);
  }



  @PostMapping("/getAllFilterBy")
  public List<ProdottoInfoEstesaProva> getFiltratiPer(@RequestBody String categoriaRichiesta) {
    return supermercatoOnlineService.ottieniFiltratiPer(categoriaRichiesta);
  }
}
