package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.response.AziendaAssociataResponse;
import com.salvio.response.DipendentiAziendaResponse;
import com.salvio.services.GestioneAziendeService;
import com.salvio.services.GestioneDipendentiService;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.digester.ArrayStack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GestioneAziendeControllerUnitTest {

  @InjectMocks
  private GestioneAziendeController gestioneAziendeController;

  @Mock
  private GestioneAziendeService gestioneAziendeService;

  @Mock
  private GestioneDipendentiService gestioneDipendentiService;

  @Test
  public void verificaAziendaControllerTestOK() {

    String codiceAzienda = "A0001";
    Integer numeroDipendentiAspettato = 3;
    List<DipendentiAziendaResponse> listaDipendentiMock = new ArrayList<>();
    listaDipendentiMock.add(
        DipendentiAziendaResponse.builder().idDipendente(9797).nome("emanuele").cognome("castagnaro").codiceFiscale("cstmln")
            .stipendio(1000.00).build());
    listaDipendentiMock.add(
        DipendentiAziendaResponse.builder().idDipendente(2222).nome("pippo").cognome("pluto").codiceFiscale("pppplt")
            .stipendio(1000.00).build());
    listaDipendentiMock.add(
        DipendentiAziendaResponse.builder().idDipendente(8888).nome("gennaro").cognome("esposito").codiceFiscale("gnnsps")
            .stipendio(2000.00).build());

    when(gestioneAziendeService.getAllBy(codiceAzienda)).thenReturn(listaDipendentiMock);

    List<DipendentiAziendaResponse> listaDipendentiOttenuta = gestioneAziendeController.getAllDipendentiFrom(codiceAzienda);

    Assertions.assertThat(listaDipendentiOttenuta.size()).isEqualTo(numeroDipendentiAspettato);


  }

  @Test
  public void verificaDipendentiControllerTestOK() {

    String codiceFiscale = "cstmnl";


    List<AziendaAssociataResponse> listaAziendeMock = new ArrayStack<>();
    listaAziendeMock.add(
        AziendaAssociataResponse.builder().codiceAzienda("A0001").nomeAzienda("finconsGroup").nomeProprietario("michele")
            .cognomeProprietario("moretti").dataFondazione("1983-01-01").nazione("ITALIA").numeroDipendenti(3)
            .monteStipendiTotale(4000.00).build());

    when(gestioneDipendentiService.getAziendeCorrelate(codiceFiscale)).thenReturn(listaAziendeMock);

    List<AziendaAssociataResponse> listaAziendeOttenute = gestioneAziendeController.getAllAziendeAssociate(codiceFiscale);


    Assertions.assertThat(listaAziendeOttenute.size()).isEqualTo(1);
  }


}
