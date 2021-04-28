package com.salvio.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.controller.AziendaDipendentiController;
import com.salvio.simulazioneprogettoarca.db.DipendenteDB;
import com.salvio.simulazioneprogettoarca.dto.DipendenteDto;
import com.salvio.simulazioneprogettoarca.repository.AziendaRepository;
import com.salvio.simulazioneprogettoarca.repository.DipendentiRepository;
import java.time.LocalDate;
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
public class AziendaDipendentiControllerUnitTest {


  @InjectMocks
  private AziendaDipendentiController aziendaDipendentiController;

  @Mock
  private AziendaRepository aziendaRepository;
  @Mock
  private DipendentiRepository dipendentiRepository;

  @Test
  public void verificaControllerTestOk() {


    String nomeAzienda = "Fincons";
    List<Integer> pIvaAziendeAssociate = new ArrayList<>();
    pIvaAziendeAssociate.add(new Integer(10));

    DipendenteDto expected = new DipendenteDto("pippo", "pluto", "pppplt");

    List<DipendenteDB> dipendentiDBMock = new ArrayList<>();
    inserisciDipendente(dipendentiDBMock, "pppplt", 10, "pippo", "pluto", "manager", 2000.00, LocalDate.of(2017, 01, 01));
    inserisciDipendente(dipendentiDBMock, "mrossi", 10, "mario", "rossi", "developer", 1000.00, LocalDate.of(2020, 01, 01));

    when(aziendaRepository.estraiAttraverso(nomeAzienda)).thenReturn(pIvaAziendeAssociate);
    when(dipendentiRepository.estraiListaDipendentiAttraverso(pIvaAziendeAssociate)).thenReturn(dipendentiDBMock);
    List<DipendenteDto> dipendentiAzienda = aziendaDipendentiController.getAllDipendentiFrom(nomeAzienda);

    verify(aziendaRepository).estraiAttraverso(nomeAzienda);
    verify(dipendentiRepository).estraiListaDipendentiAttraverso(pIvaAziendeAssociate);

    Assertions.assertThat(dipendentiAzienda.get(0)).isEqualToComparingFieldByField(expected);
    Assertions.assertThat(dipendentiAzienda.size()).isEqualTo(2);
  }


  private void inserisciDipendente(List<DipendenteDB> lista, String cf, Integer idAzienda, String nome, String cognome,
      String ruolo, Double stipendio, LocalDate assunzione) {

    lista.add(new DipendenteDB(idAzienda, cf, nome, cognome, ruolo, stipendio, assunzione));
  }

}
