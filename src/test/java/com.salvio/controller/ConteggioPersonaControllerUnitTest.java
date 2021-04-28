package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.controller.ConteggioPersonaController;
import com.salvio.simulazioneprogettoarca.repository.ConteggioPersonaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ConteggioPersonaControllerUnitTest {

  @InjectMocks
  private ConteggioPersonaController conteggioPersonaController;

  @Mock
  private ConteggioPersonaRepository conteggioPersonaRepository;


  @Test
  public void verificaControllerTestOK(){

    String maschile="M";
    String femminile="F";
    Integer numeroPersoneMaschi=1;
    Integer numeroPersoneFemmine=2;
    when(conteggioPersonaRepository.estraiCardinalitaSesso(maschile)).thenReturn(numeroPersoneMaschi);
    when(conteggioPersonaRepository.estraiCardinalitaSesso(femminile)).thenReturn(numeroPersoneFemmine);

   String calcoloRisultato = conteggioPersonaController.getNumerositaSesso();

    String expected= "il numero di persone di sesso maschile e: 1, il numero di persone di sesso femminile e: 2";;
    Assertions.assertThat(calcoloRisultato).isEqualTo(expected);
  }


}
