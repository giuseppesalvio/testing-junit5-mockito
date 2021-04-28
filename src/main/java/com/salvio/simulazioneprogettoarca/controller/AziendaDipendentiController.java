package com.salvio.simulazioneprogettoarca.controller;

import com.salvio.simulazioneprogettoarca.db.DipendenteDB;
import com.salvio.simulazioneprogettoarca.dto.DipendenteDto;
import com.salvio.simulazioneprogettoarca.repository.AziendaRepository;
import com.salvio.simulazioneprogettoarca.repository.DipendentiRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/azienda/dipendenti")
public class AziendaDipendentiController {

  private final AziendaRepository aziendaRepository;

  private final DipendentiRepository dipendentiRepository;


  public AziendaDipendentiController(AziendaRepository aziendaRepository,
      DipendentiRepository dipendentiRepository) {
    this.aziendaRepository = aziendaRepository;
    this.dipendentiRepository = dipendentiRepository;
  }


  @GetMapping("/get-all-from")
  public List<DipendenteDto> getAllDipendentiFrom(@RequestParam String nomeAzienda) {

   List<DipendenteDto> listaDaRestiruire= new ArrayList<>();
   List<Integer> aziendeAssociate= aziendaRepository.estraiAttraverso(nomeAzienda);

   List<DipendenteDB> listaDB= dipendentiRepository.estraiListaDipendentiAttraverso(aziendeAssociate);

   for (DipendenteDB dipendente :listaDB ){

     listaDaRestiruire.add(new DipendenteDto(dipendente.getNome(), dipendente.getCognome(), dipendente.getCodiceFiscale()));
   }

   return listaDaRestiruire;
  }
}
