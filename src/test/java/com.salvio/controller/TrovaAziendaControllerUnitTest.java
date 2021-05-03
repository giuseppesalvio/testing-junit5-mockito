package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.controller.TrovaAziendaController;
import com.salvio.simulazioneprogettoarca.repository.TrovaAziendaRepository;
import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RestController;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TrovaAziendaControllerUnitTest {


  @InjectMocks
  private TrovaAziendaController trovaAziendaController;

  @Mock
  private TrovaAziendaRepository trovaAziendaRepository;
  @Test
  public void verificaControllerTestOk(){
    int pIva=1000;

    when(trovaAziendaRepository.ottieniNomeAzienda(pIva)).thenReturn("arca");

    String risposta = trovaAziendaController.getNomeAziendaBy(pIva);


    Assertions.assertThat(risposta).isEqualTo("Messaggio: l'azienda si chiama: arca");

  }

}
