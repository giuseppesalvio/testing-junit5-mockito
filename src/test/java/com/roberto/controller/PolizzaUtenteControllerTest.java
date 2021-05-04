package com.roberto.controller;

import com.roberto.entitys.Anagrafica;
import com.roberto.entitys.PolizzaUtente;
import com.roberto.models.PolizzeUtenteFE;
import com.roberto.repository.AnagraficaRepository;
import com.roberto.repository.RepositoryPolizzeUtente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class PolizzaUtenteControllerTest
{

    @InjectMocks
    PolizzeUtenteController polizzaUtenteController;

    @Mock
    RepositoryPolizzeUtente repositoryUtentePolizze;

    @Test
    public void test_controllerTestok()
    {
        //arrange
        when(repositoryUtentePolizze.getPolizzeutenteByFscalCode("tstCodFisc")).thenReturn(new PolizzaUtente(Long.valueOf("1"),"tstCodFisc",Long.valueOf("1")));
        //act
        PolizzeUtenteFE actual =polizzaUtenteController.getPolizzeUtenteByFiscalCode("tstCodFisc");

        //assert
        PolizzeUtenteFE expected=new PolizzeUtenteFE("tstCodFisc",Long.valueOf(1));
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
        //TODO dopo aver finito questo qui rilancio il test end to end e noto che è ancora ko perche non ho implementato effetivamente il repository?
        //TODO quando sostituisco il repository effettivo nel controller che ora è null?
    }

}
