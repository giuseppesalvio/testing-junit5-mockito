package com.salvio.simulazioneprogettoarca.controller;

import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.dto.PersonaDTO;
import com.salvio.simulazioneprogettoarca.service.PersonaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class PersonaController {

  private final PersonaService personaService;

  public PersonaController(PersonaService personaService) {
    this.personaService = personaService;
  }

  @PostMapping("/getByNomeCognome")
  public List<PersonaDTO> getPersonaByNomeCognomeDTO(@RequestBody DatiAnagraficiDTO inputDto) {

    List<PersonaDTO>listaInfoFE=new ArrayList();
    List<PersonaDB> listaInfoBE=personaService.ottieniPersonaBy(inputDto);

    //mapping da BE a FE
    for (PersonaDB personaDB : listaInfoBE)
    {
      listaInfoFE.add(new PersonaDTO(personaDB.getNome(),personaDB.getCognome(),personaDB.getCodiceFiscale(),
          personaDB.getIndirizzo()));
    }

    return listaInfoFE;
  }
}
