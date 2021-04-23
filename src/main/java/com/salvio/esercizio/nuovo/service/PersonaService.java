package com.salvio.esercizio.nuovo.service;

import com.salvio.esercizio.nuovo.db.PersonaDB;
import com.salvio.esercizio.nuovo.dto.NomeCognomeDto;
import com.salvio.esercizio.nuovo.repository.PersonaRepository;
import com.salvio.repository.AnagraficaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;


    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public PersonaDB getPersonaBy(NomeCognomeDto nomeCognomeDto) {
        return personaRepository.getPersonaBy(nomeCognomeDto);
    }
}
