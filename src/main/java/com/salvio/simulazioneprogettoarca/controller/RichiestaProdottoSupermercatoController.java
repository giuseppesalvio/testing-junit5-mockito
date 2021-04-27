package com.salvio.simulazioneprogettoarca.controller;

import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import com.salvio.simulazioneprogettoarca.dto.RichiestaProdottoDto;
import com.salvio.simulazioneprogettoarca.dto.SupermercatoDto;
import com.salvio.simulazioneprogettoarca.service.RichiestaProdottoSupermercatoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supermercato")
public class RichiestaProdottoSupermercatoController {

  private final RichiestaProdottoSupermercatoService richiestaProdottoSupermercatoService;

  public RichiestaProdottoSupermercatoController(
      RichiestaProdottoSupermercatoService richiestaProdottoSupermercatoService) {
    this.richiestaProdottoSupermercatoService = richiestaProdottoSupermercatoService;
  }

  @PostMapping("/get-lista-supermercati-validi-in-zona")
  public List<SupermercatoDto> getSupermercatiValidiBy(@RequestBody RichiestaProdottoDto richiestaProdottoDto) {

    List<SupermercatoDB> listaService=richiestaProdottoSupermercatoService.ottieniSupermercatiValidiAttraverso(richiestaProdottoDto);
    List<SupermercatoDto>  listaFE= new ArrayList<>();

    //mapping
    for (SupermercatoDB sup : listaService ){

      listaFE.add(new SupermercatoDto(sup.getNomeSupermercato(),sup.getVia(),sup.getCitta(),sup.getProvincia()));
    }
    return listaFE;
  }
}
