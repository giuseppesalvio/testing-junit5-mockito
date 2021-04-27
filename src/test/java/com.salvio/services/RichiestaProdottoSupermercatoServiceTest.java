package com.salvio.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import com.salvio.simulazioneprogettoarca.dto.RichiestaProdottoDto;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoMagazzinoProdottiRepository;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoSupermercatoRepository;
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
public class RichiestaProdottoSupermercatoServiceTest {

  @InjectMocks
  private RichiestaProdottoSupermercatoService richiestaProdottoSupermercatoService;

  @Mock
  private RichiestaInfoMagazzinoProdottiRepository richiestaInfoMagazzinoProdottiRepository;

  @Mock
  private RichiestaInfoSupermercatoRepository richiestaInfoSupermercatoRepository;


  @Test
  public void verificaServiceTestOk() {

    RichiestaProdottoDto richiestaDto = new RichiestaProdottoDto("pane", 10, "verona");
    List<SupermercatoDB> listaMock = new ArrayList<>();
    listaMock.add(new SupermercatoDB(1, "coop", "garibaldi", "baldaria", "verona"));
    listaMock.add(new SupermercatoDB(2, "lidl", "mazzini", "minerbe", "verona"));
    listaMock.add(new SupermercatoDB(3, "eurospin", "cavour", "legnago", "verona"));


    List<Integer> listaIdSupermercatiValidi = new ArrayList<>();
    listaIdSupermercatiValidi.add(1);
    listaIdSupermercatiValidi.add(2);

    when(richiestaInfoSupermercatoRepository.estraiListaFiltrataPer(richiestaDto.getProvinciaRichiesta())).thenReturn(listaMock);
    when(richiestaInfoMagazzinoProdottiRepository
        .estraiIdSupermercatiDisponibili(richiestaDto.getNomeProdottoRichiesto(), richiestaDto.getNumerositaRichiesta()))
        .thenReturn(listaIdSupermercatiValidi);

    List<SupermercatoDB> listaDB = richiestaProdottoSupermercatoService.ottieniSupermercatiValidiAttraverso(richiestaDto);

    verify(richiestaInfoSupermercatoRepository).estraiListaFiltrataPer(richiestaDto.getProvinciaRichiesta());
    verify(richiestaInfoMagazzinoProdottiRepository)
        .estraiIdSupermercatiDisponibili(richiestaDto.getNomeProdottoRichiesto(), richiestaDto.getNumerositaRichiesta());

    Assertions.assertThat(listaDB.size()).isEqualTo(2);


  }

}
