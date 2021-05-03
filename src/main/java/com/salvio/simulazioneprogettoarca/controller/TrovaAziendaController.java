package com.salvio.simulazioneprogettoarca.controller;

import com.salvio.simulazioneprogettoarca.repository.TrovaAziendaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrovaAziendaController {

  private final TrovaAziendaRepository trovaAziendaRepository;

  public TrovaAziendaController(TrovaAziendaRepository trovaAziendaRepository) {
    this.trovaAziendaRepository = trovaAziendaRepository;
  }


  @GetMapping("/get-nome-azienda-from")
  public String getNomeAziendaBy(@RequestParam int pIva) {
    return "Messaggio: l'azienda si chiama: " + trovaAziendaRepository.ottieniNomeAzienda(pIva);
  }
}
