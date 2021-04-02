package com.salvio.controller;


import com.salvio.entitys.DettaglioPolizza;
import com.salvio.services.RicercaAnagraficheDiPolizzaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RicercaAnagraficheDiPolizzaController {

    private final RicercaAnagraficheDiPolizzaService ricercaAnagrafichePerPolizzaService;

    public RicercaAnagraficheDiPolizzaController(RicercaAnagraficheDiPolizzaService ricercaAnagrafichePerPolizzaService) {
        this.ricercaAnagrafichePerPolizzaService = ricercaAnagrafichePerPolizzaService;
    }

    @PostMapping("/ricerca-anagrafiche-di-polizza")
    public List<DettaglioPolizza> execute(@RequestBody String codiceFiscale) {
        return ricercaAnagrafichePerPolizzaService.execute(codiceFiscale);
    }
}
