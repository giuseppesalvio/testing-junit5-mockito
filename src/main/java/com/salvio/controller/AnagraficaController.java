package com.salvio.controller;

import com.salvio.entitys.Anagrafica;
import com.salvio.repository.AnagraficaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnagraficaController {

  private AnagraficaRepository anagraficaRepository;

  public AnagraficaController(AnagraficaRepository anagraficaRepository) {
    this.anagraficaRepository = anagraficaRepository;
  }

  @PostMapping("/insert")
  public int getTotaleAccountBancaPost(@RequestBody Anagrafica anagrafica) {
    return anagraficaRepository.insert(anagrafica);
  }

  @GetMapping("/getAll")
  public List<Anagrafica> getAll() {
    return anagraficaRepository.getAll();
  }

  //TODO inserisci o modifica anagrafica a seconda se l'id è gia presente o meno
  @PostMapping("/inserisciModifica")
  public Anagrafica modificaAnagrafica(@RequestBody Anagrafica anagrafica) {
    return new Anagrafica();
  }

}
