package com.salvio.simulazioneprogettoarca.controller;

import com.salvio.simulazioneprogettoarca.repository.ConteggioPersonaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ConteggioPersonaController {

  private final ConteggioPersonaRepository conteggioPersonaRepository;

  public ConteggioPersonaController(
      ConteggioPersonaRepository conteggioPersonaRepository) {
    this.conteggioPersonaRepository = conteggioPersonaRepository;
  }

  @GetMapping("/get-conteggio-numerosita-sesso")
  public String getNumerositaSesso() {
    Integer numeroMaschi = conteggioPersonaRepository.estraiCardinalitaSesso("M");
    Integer numeroFemmine = conteggioPersonaRepository.estraiCardinalitaSesso("F");

    String result = "il numero di persone di sesso maschile e: " + numeroMaschi +
        ", il numero di persone di sesso femminile e: " + numeroFemmine;

    return result;

  }
}
