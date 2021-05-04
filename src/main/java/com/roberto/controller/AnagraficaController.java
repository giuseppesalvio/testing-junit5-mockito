package com.roberto.controller;

import com.roberto.entitys.Anagrafica;
import com.roberto.repository.AnagraficaRepository;
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
    return anagraficaRepository.getAllAnagrafiche();
  }

  //TODO inserisci o modifica anagrafica a seconda se l'id è gia presente o meno
  @PostMapping("/inserisciModifica")
  public Anagrafica modificaAnagrafica(@RequestBody Anagrafica anagrafica) {
    return new Anagrafica();
  }

  public List<Anagrafica> getAllTestTdd() {
    return anagraficaRepository.getAllAnagrafiche();
  }

  public List<Anagrafica> getAllTestTdd(int i) {
    return anagraficaRepository.getAllAnagrafiche(i);
  }
}
