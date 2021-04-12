package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.InformazioniAnagraficaRepository;
import com.salvio.repository.InformazioniPolizzaRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InformazioniDettaglioPolizzaServiceTest {

  @InjectMocks
  private InformazioniPolizzaService informazioniPolizzaService;

  @Mock
  private InformazioniPolizzaRepository informazioniPolizzaRepository;
  @Mock
  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;

  @Test
  public void controlloExecuteService() {
    String codiceFiscale = "123456";
    AnagraficaProva anagraficaProva = AnagraficaProva.builder().idAnagrafica(9999).codiceFiscale("123456").build();
    List<PolizzaProva> listaPolizzeDefault = new ArrayList<>();
    listaPolizzeDefault.add(new PolizzaProva(1, 9999, 9999, 9999));
    listaPolizzeDefault.add(new PolizzaProva(2, 8888, 9999, 2222));

    List<AnagraficaProva> listaAnagraficheDefault = new ArrayList<>();

    listaAnagraficheDefault
        .add(AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build());
    listaAnagraficheDefault
        .add(AnagraficaProva.builder().idAnagrafica(8888).nome("gennaro").cognome("esposito").codiceFiscale("gnnsps").build());
    listaAnagraficheDefault
        .add(AnagraficaProva.builder().idAnagrafica(2222).nome("pippo").cognome("pluto").codiceFiscale("pppplt").build());


    when(informazioniAnagraficaRepository.getAnagraficaDaCodiceFiscale(codiceFiscale)).thenReturn(anagraficaProva);
    when(informazioniPolizzaRepository.getListaPolizzeAssociateAIdAnagrafica(anagraficaProva.getIdAnagrafica()))
        .thenReturn(listaPolizzeDefault);

    //refactora e crea un metodo esterno da richiamare
    when(informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(9999))
        .thenReturn(listaAnagraficheDefault.get(0));

    when(informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(8888))
        .thenReturn(listaAnagraficheDefault.get(1));

    when(informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(2222))
        .thenReturn(listaAnagraficheDefault.get(2));



    List<DettaglioPolizzaProva> listaDettaglioFornitaDalService = informazioniPolizzaService
        .estraiEProcessaInformazioni(codiceFiscale);



    //verifico che mi siano restituite
    Assertions.assertThat(listaDettaglioFornitaDalService.size()).isEqualTo(2);

    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getNumeroPolizza()).isEqualTo(1);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdContraente()).isEqualTo(9999);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdAssicurato()).isEqualTo(9999);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdBeneficiario()).isEqualTo(9999);

    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getContraente().getNome()).isEqualTo("mario");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getContraente().getCognome()).isEqualTo("rossi");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getContraente().getCodiceFiscale()).isEqualTo("123456");

    //seconda DettaglioPolizzaProva

    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getPolizza().getNumeroPolizza()).isEqualTo(2);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getPolizza().getIdContraente()).isEqualTo(8888);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getPolizza().getIdAssicurato()).isEqualTo(9999);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getPolizza().getIdBeneficiario()).isEqualTo(2222);

    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getContraente().getNome()).isEqualTo("gennaro");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getContraente().getCognome()).isEqualTo("esposito");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getContraente().getCodiceFiscale()).isEqualTo("gnnsps");

    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getBeneficiario().getNome()).isEqualTo("pippo");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getBeneficiario().getCognome()).isEqualTo("pluto");
    Assertions.assertThat(listaDettaglioFornitaDalService.get(1).getBeneficiario().getCodiceFiscale()).isEqualTo("pppplt");




  }

}
