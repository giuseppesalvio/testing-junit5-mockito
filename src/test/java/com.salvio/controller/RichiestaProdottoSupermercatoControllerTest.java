package com.salvio.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.controller.RichiestaProdottoSupermercatoController;
import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import com.salvio.simulazioneprogettoarca.dto.RichiestaProdottoDto;
import com.salvio.simulazioneprogettoarca.dto.SupermercatoDto;
import com.salvio.simulazioneprogettoarca.service.RichiestaProdottoSupermercatoService;
import java.util.ArrayList;
import java.util.List;
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
public class RichiestaProdottoSupermercatoControllerTest {

  @InjectMocks
  private RichiestaProdottoSupermercatoController richiestaProdottoSupermercatoController;

  @Mock
  private RichiestaProdottoSupermercatoService richiestaProdottoSupermercatoService;

  @Test
  public void verificaControllerTestOk(){
    RichiestaProdottoDto richiestaProdottoDto=
                        new RichiestaProdottoDto("pane", 10, "verona");

    List<SupermercatoDB> listaMock=new ArrayList<>();
    listaMock.add(new SupermercatoDB(1,"coop","garibaldi","baldaria","verona"));
    listaMock.add(new SupermercatoDB(2,"lidl","Mazzini","minerbe","verona"));

    when(richiestaProdottoSupermercatoService.ottieniSupermercatiValidiAttraverso(richiestaProdottoDto)).thenReturn(listaMock);


    List<SupermercatoDto> listaSuperValidi=richiestaProdottoSupermercatoController.getSupermercatiValidiBy(richiestaProdottoDto);

    verify(richiestaProdottoSupermercatoService).ottieniSupermercatiValidiAttraverso(richiestaProdottoDto);

    Assertions.assertThat(listaSuperValidi.get(1)).isEqualToComparingFieldByField(listaMock.get(1));

  }

}
