package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.services.InformazioniPolizzaService;
import com.salvio.services.OperazioniService;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InformazioniDettaglioPolizzaControllerTest {

  @InjectMocks
  private InformazioniPolizzaController informazioniPolizzaController;

  @Mock
  private InformazioniPolizzaService informazioniPolizzaService;
  @Mock
  private OperazioniService operazioniService;

  @Test
  public void controlloExecuteOperazioniPolizzaController() {


    String contentJsonStringRequest = "{\n"
        + "   \"idOperazione\":1,\n"
        + "   \"numeroPolizzaInteressata\":1,\n"
        + "   \"idAnagraficaInteressata\":9999\n"
        + "}";
    PolizzaProva polizzaProva= new PolizzaProva(1,9999,9999,9999);

    when(operazioniService.mostraPolizzaProvaRichiesta(1)).thenReturn(polizzaProva);

   PolizzaProva polizzaRestituita =informazioniPolizzaController.gestisciEndPointPolizze(contentJsonStringRequest);


   Assertions.assertThat(polizzaRestituita.getIdContraente()).isEqualTo(9999);


  }

  @Test
  public void controlloExecuteController() {

    String codiceFiscale = "123456";


    List<DettaglioPolizzaProva> listaExpected = new ArrayList<>();

    //refactora creando un metodo che inserisca i dati

    listaExpected.add(new DettaglioPolizzaProva(new PolizzaProva(1, 9999, 9999, 9999),
        AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build(),
        AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build(),
        AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build()));
    listaExpected.add(new DettaglioPolizzaProva(new PolizzaProva(2, 8888, 9999, 2222),
        AnagraficaProva.builder().idAnagrafica(8888).nome("gennaro").cognome("esposito").codiceFiscale("gnnsps").build(),
        AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build(),
        AnagraficaProva.builder().idAnagrafica(2222).nome("pippo").cognome("pluto").codiceFiscale("pppplt").build()));



    when(informazioniPolizzaService.getDettaglioPolizza(codiceFiscale)).thenReturn(listaExpected);

    List<DettaglioPolizzaProva> lista = informazioniPolizzaController.gestisciEndpoint(codiceFiscale);

    Assertions.assertThat(lista.size()).isEqualTo(2);


    Assertions.assertThat(lista.get(0).getPolizza().getNumeroPolizza())
        .isEqualTo(listaExpected.get(0).getPolizza().getNumeroPolizza());
    Assertions.assertThat(lista.get(0).getPolizza().getIdContraente())
        .isEqualTo(listaExpected.get(0).getPolizza().getIdContraente());
    Assertions.assertThat(lista.get(0).getPolizza().getIdAssicurato())
        .isEqualTo(listaExpected.get(0).getPolizza().getIdAssicurato());
    Assertions.assertThat(lista.get(0).getPolizza().getIdBeneficiario())
        .isEqualTo(listaExpected.get(0).getPolizza().getIdBeneficiario());

    Assertions.assertThat(lista.get(0).getContraente().getCodiceFiscale())
        .isEqualTo(listaExpected.get(0).getContraente().getCodiceFiscale());
    Assertions.assertThat(lista.get(0).getAssicurato().getCodiceFiscale())
        .isEqualTo(listaExpected.get(0).getAssicurato().getCodiceFiscale());
    Assertions.assertThat(lista.get(0).getBeneficiario().getCodiceFiscale())
        .isEqualTo(listaExpected.get(0).getBeneficiario().getCodiceFiscale());

    // assertions seconda DettaglioPolizza
    Assertions.assertThat(lista.get(1).getPolizza().getNumeroPolizza())
        .isEqualTo(listaExpected.get(1).getPolizza().getNumeroPolizza());
    Assertions.assertThat(lista.get(1).getPolizza().getIdContraente())
        .isEqualTo(listaExpected.get(1).getPolizza().getIdContraente());
    Assertions.assertThat(lista.get(1).getPolizza().getIdAssicurato())
        .isEqualTo(listaExpected.get(1).getPolizza().getIdAssicurato());
    Assertions.assertThat(lista.get(1).getPolizza().getIdBeneficiario())
        .isEqualTo(listaExpected.get(1).getPolizza().getIdBeneficiario());

    Assertions.assertThat(lista.get(1).getContraente().getCodiceFiscale())
        .isEqualTo(listaExpected.get(1).getContraente().getCodiceFiscale());
    Assertions.assertThat(lista.get(1).getAssicurato().getCodiceFiscale())
        .isEqualTo(listaExpected.get(1).getAssicurato().getCodiceFiscale());
    Assertions.assertThat(lista.get(1).getBeneficiario().getCodiceFiscale())
        .isEqualTo(listaExpected.get(1).getBeneficiario().getCodiceFiscale());


  }




}
