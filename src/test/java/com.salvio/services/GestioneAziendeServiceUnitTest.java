package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.response.DipendentiAziendaResponse;
import com.salvio.entitys.AnagraficaAzienda;
import com.salvio.entitys.Dipendente;
import com.salvio.repository.GestioneAnagraficheRepository;
import com.salvio.repository.GestioneDipendentiRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GestioneAziendeServiceUnitTest {


  @InjectMocks
  private GestioneAziendeService gestioneAziendeService;

  @Mock
  private GestioneDipendentiRepository gestioneDipendentiRepository;
  @Mock
  private GestioneAnagraficheRepository gestioneAnagraficheRepository;

  @Test
  public void verificaAziendaServiceTestOk() {

    String codiceAzienda = "A0001";
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

    AnagraficaAzienda anagraficaAzienda1 = AnagraficaAzienda.builder().idAnagrafica(9797).nome("emanuele").cognome("castagnaro")
        .codiceFiscale("cstmnl").build();

    AnagraficaAzienda anagraficaAzienda2 = AnagraficaAzienda.builder().idAnagrafica(2222).nome("pippo").cognome("pluto")
        .codiceFiscale("pppplt").build();

    AnagraficaAzienda anagraficaAzienda3 =
        AnagraficaAzienda.builder().idAnagrafica(8888).nome("gennaro").cognome("esposito").codiceFiscale("gnnsps").build();



    when(gestioneDipendentiRepository.getListaDipendentiBy(codiceAzienda)).thenReturn(listaDipendentiMock);
    when(gestioneAnagraficheRepository.getAnagraficaBy(9797)).thenReturn(anagraficaAzienda1);
    when(gestioneAnagraficheRepository.getAnagraficaBy(2222)).thenReturn(anagraficaAzienda2);
    when(gestioneAnagraficheRepository.getAnagraficaBy(8888)).thenReturn(anagraficaAzienda3);

    List<DipendentiAziendaResponse> listaOttenuta = gestioneAziendeService.getAllBy(codiceAzienda);

    Assertions.assertThat(listaOttenuta.size()).isEqualTo(3);
    Assertions.assertThat(listaOttenuta.get(0).getIdDipendente()).isEqualTo(9797);
    Assertions.assertThat(listaOttenuta.get(0).getNome()).isEqualTo("emanuele");
    Assertions.assertThat(listaOttenuta.get(0).getCognome()).isEqualTo("castagnaro");
    Assertions.assertThat(listaOttenuta.get(0).getCodiceFiscale()).isEqualTo("cstmnl");
    Assertions.assertThat(listaOttenuta.get(0).getStipendio()).isEqualTo(1000.00);


    Assertions.assertThat(listaOttenuta.get(1).getIdDipendente()).isEqualTo(2222);
    Assertions.assertThat(listaOttenuta.get(2).getIdDipendente()).isEqualTo(8888);


  }


}
