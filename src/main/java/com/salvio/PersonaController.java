package com.salvio;

import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import com.salvio.dto.NomeCognomeDto1;
import com.salvio.dto.PersonaDto1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonaController {

    @PostMapping("/getByNomeCognome")
    public PersonaDto1 personaGetByNomeCognome(@RequestBody NomeCognomeDto1 nomeCognomeDto1)  {
        return null;
    }
}
