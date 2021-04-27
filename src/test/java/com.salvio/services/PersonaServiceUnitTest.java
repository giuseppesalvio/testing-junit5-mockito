package com.salvio.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.repository.PersonaRepository;
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
public class PersonaServiceUnitTest {

  @InjectMocks
  private PersonaService personaService;

  @Mock
  private PersonaRepository personaRepository;

  @Test
  public void verificaPersonaServiceTestOk(){
    DatiAnagraficiDTO inputDto = new DatiAnagraficiDTO("mario", "rossi");
    List<PersonaDB> listaRepoMockata= new ArrayList<>();
    listaRepoMockata.add(new PersonaDB(1,"mario","rossi","mrossi70",99999));
    listaRepoMockata.add( new PersonaDB(2,"mario","rossi","mrossi95",33333));
    when(personaRepository.estraiAttraverso(inputDto)).thenReturn(listaRepoMockata);

    List<PersonaDB> listaOttenuta= personaService.ottieniPersonaBy(inputDto);

    verify(personaRepository).estraiAttraverso(inputDto);

    Assertions.assertThat(listaOttenuta.size()).isEqualTo(2);


  }

}
