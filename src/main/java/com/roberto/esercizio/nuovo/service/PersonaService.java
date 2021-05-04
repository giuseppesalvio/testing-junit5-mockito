package com.roberto.esercizio.nuovo.service;

import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.roberto.esercizio.nuovo.repository.PersonaRepository;
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
