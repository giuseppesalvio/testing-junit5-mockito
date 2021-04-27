package com.salvio.simulazioneprogettoarca.service;

import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.repository.PersonaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

  private final PersonaRepository personaRepository;

  public PersonaService(PersonaRepository personaRepository) {
    this.personaRepository = personaRepository;
  }

  public List<PersonaDB> ottieniPersonaBy(DatiAnagraficiDTO inputDto) {

    return personaRepository.estraiAttraverso(inputDto);
  }
}
