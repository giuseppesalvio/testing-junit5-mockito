package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.Response.AziendaAssociataResponse;
import com.salvio.entitys.AnagraficaAzienda;
import com.salvio.entitys.Dipendente;
import com.salvio.entitys.Azienda;
import com.salvio.repository.GestioneAnagraficheRepository;
import com.salvio.repository.GestioneAziendeRepository;
import com.salvio.repository.GestioneDipendentiRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GestioneDipendentiServiceUnitTest {

  @InjectMocks
  private GestioneDipendentiService gestioneDipendentiService;

  @Mock
  private GestioneDipendentiRepository gestioneDipendentiRepository;

  @Mock
  private GestioneAnagraficheRepository gestioneAnagraficheRepository;

  @Mock
  private GestioneAziendeRepository gestioneAziendeRepository;


  @Test
  public void verificaDipendentiServiceTestOk() {

    String codiceFiscaleDipendente = "cstmnl";
    AnagraficaAzienda anagraficaCorrelataProva = AnagraficaAzienda.builder().idAnagrafica(9797).build();

    Dipendente dipendenteProva = Dipendente.builder().idDipendente(9797).codiceAzienda("A0001").build();

    Azienda infoAzienda = Azienda.builder().codiceAzienda("A0001").nomeAzienda("finconsGroup")
        .codiceFiscaleProprietarioAzienda("mrtmcl").dataFondazione("1983-01-01").nazione("ITALIA").build();

    AnagraficaAzienda anagraficaProprietario = AnagraficaAzienda.builder().idAnagrafica(0000).nome("michele").cognome("moretti")
        .codiceFiscale("mrtmcl").build();

    List<Dipendente> listaDipendentiMock = new ArrayList<>();

    listaDipendentiMock.add(
        Dipendente.builder().idDipendente(9797).codiceAzienda("A0001").ruolo("developer").dataAssunzione("2021-03-01")
            .stipendio(1000.00).build());

    listaDipendentiMock.add(
        Dipendente.builder().idDipendente(2222).codiceAzienda("A0001").ruolo("developer").dataAssunzione("2021-01-01")
            .stipendio(1000.00).build());

    listaDipendentiMock.add(
        Dipendente.builder().idDipendente(8888).codiceAzienda("A0001").ruolo("manager").dataAssunzione("2019-01-01")
            .stipendio(2000.00).build());

    when(gestioneAnagraficheRepository.getAnagraficaCorrelataA(codiceFiscaleDipendente)).thenReturn(anagraficaCorrelataProva);
    when(gestioneDipendentiRepository.getDipendenteAttraverso(anagraficaCorrelataProva.getIdAnagrafica()))
        .thenReturn(dipendenteProva);
    when(gestioneAziendeRepository.getInfoAziendaBy(dipendenteProva.getCodiceAzienda())).thenReturn(infoAzienda);
    when(gestioneAnagraficheRepository.getAnagraficaCorrelataA(infoAzienda.getCodiceFiscaleProprietarioAzienda()))
        .thenReturn(anagraficaProprietario);
    when(gestioneDipendentiRepository.getListaDipendentiBy(infoAzienda.getCodiceAzienda())).thenReturn(listaDipendentiMock);

    List<AziendaAssociataResponse> listaOttenuta = gestioneDipendentiService.getAziendeCorrelate(codiceFiscaleDipendente);

    Assertions.assertThat(listaOttenuta.size()).isEqualTo(1);
    Assertions.assertThat(listaOttenuta.get(0).getNomeProprietario()).isEqualTo("michele");
    Assertions.assertThat(listaOttenuta.get(0).getCognomeProprietario()).isEqualTo("moretti");
    Assertions.assertThat(listaOttenuta.get(0).getNumeroDipendenti()).isEqualTo(3);
    Assertions.assertThat(listaOttenuta.get(0).getMonteStipendiTotale()).isEqualTo(4000.00);


  }


}
