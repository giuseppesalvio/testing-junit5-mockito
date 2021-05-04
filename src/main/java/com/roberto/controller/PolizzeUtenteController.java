package com.roberto.controller;

import com.roberto.entitys.PolizzaUtente;
import com.roberto.models.PolizzeUtenteFE;
import com.roberto.repository.RepositoryPolizzeUtente;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utente")
public class PolizzeUtenteController
{
    final RepositoryPolizzeUtente repositoryPolizzeUtente;

    public PolizzeUtenteController(RepositoryPolizzeUtente repositoryPolizzeUtente) {
        this.repositoryPolizzeUtente = repositoryPolizzeUtente;
    }

    @GetMapping("/polizzaUtenteByFc")
    public PolizzeUtenteFE getPolizzeUtenteByFiscalCode(@Param("codiceFiscale") String codiceFiscale)
    {
       return map(repositoryPolizzeUtente.getPolizzeutenteByFscalCode(codiceFiscale));
    }

    private PolizzeUtenteFE map(PolizzaUtente polizzeutenteByFscalCode)
    {
        //TODO  mapping
        PolizzeUtenteFE result=new PolizzeUtenteFE(polizzeutenteByFscalCode.getNumeroPolizza(),polizzeutenteByFscalCode.getUtenteid());
        return result;
    }
}
