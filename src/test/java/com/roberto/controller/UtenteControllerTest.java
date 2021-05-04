package com.roberto.controller;

import com.roberto.entitys.Utente;
import com.roberto.models.UtenteFE;
import com.roberto.repository.UtenteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UtenteControllerTest {

    @InjectMocks
    UtenteController utenteController;
    @Mock
    UtenteRepository utenteRepository;

    @Test
    void recupaUtenteByCodiceFiscale() {
        when(utenteRepository.estrai("marioCodiceFiscale")).thenReturn(new Utente(1,"mario","rossi","marioCodiceFiscale"));

        UtenteFE utenteFe = utenteController.recupaUtenteByCodiceFiscale("marioCodiceFiscale");

        UtenteFE expected = new UtenteFE("mario","rossi");
        Assertions.assertThat(utenteFe).isEqualToComparingFieldByField(expected);
    }
}
