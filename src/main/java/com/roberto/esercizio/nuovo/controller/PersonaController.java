package com.roberto.esercizio.nuovo.controller;


import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.roberto.esercizio.nuovo.dto.PersonaDto;
import com.roberto.esercizio.nuovo.service.PersonaService;
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
    public PersonaDto trovaPersonaBy(@RequestBody NomeCognomeDto nomeCognomeDto) {
        PersonaDB personaDB = personaService.getPersonaBy(nomeCognomeDto);
        return new PersonaDto(personaDB.getId(),personaDB.getIndirizzo());
    }
}
