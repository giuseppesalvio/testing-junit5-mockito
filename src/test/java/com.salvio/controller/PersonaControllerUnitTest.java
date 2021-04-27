package com.salvio.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.controller.PersonaController;
import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.dto.PersonaDTO;
import com.salvio.simulazioneprogettoarca.service.PersonaService;
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
public class PersonaControllerUnitTest {

  @InjectMocks
  private PersonaController personaController;

  @Mock
  private PersonaService personaService;

  @Test
 public void verificaControllerUnitTestOk(){

    DatiAnagraficiDTO inputDto = new DatiAnagraficiDTO("mario", "rossi");
    List<PersonaDB> listaRestituitaDalService= new ArrayList<>();
    listaRestituitaDalService.add(new PersonaDB(1,"mario","rossi","mrossi70",99999));
    listaRestituitaDalService.add(new PersonaDB(2,"mario","rossi","mrossi95",33333));

    when(personaService.ottieniPersonaBy(inputDto)).thenReturn(listaRestituitaDalService);

    List<PersonaDTO> listaCollegata= personaController.getPersonaByNomeCognomeDTO(inputDto);

    verify(personaService).ottieniPersonaBy(inputDto);

    Assertions.assertThat(listaCollegata.size()).isEqualTo(2);


  }




}
