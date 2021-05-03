package com.salvio.controller;

import com.salvio.entitys.Utente;
import com.salvio.models.UtenteFE;
import com.salvio.repository.UtenteRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/utente")
public class UtenteController {

  private final UtenteRepository utenteRepository;

  public UtenteController(UtenteRepository utenteRepository) {
    this.utenteRepository = utenteRepository;
  }

  @GetMapping("/getByCodiceFiscale")
  public UtenteFE recupaUtenteByCodiceFiscale(@RequestParam String codiceFiscale) {
    return map(utenteRepository.estrai(codiceFiscale));
  }

  private UtenteFE map(Utente utente) {
    return new UtenteFE(
            utente.getNome(),
            utente.getCognome()
    );
  }
}

