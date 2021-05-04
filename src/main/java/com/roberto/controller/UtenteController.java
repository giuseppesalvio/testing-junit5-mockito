package com.roberto.controller;

import com.roberto.entitys.Utente;
import com.roberto.models.UtenteFE;
import com.roberto.repository.UtenteRepository;
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

