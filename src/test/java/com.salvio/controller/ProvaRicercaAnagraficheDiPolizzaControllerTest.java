package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.entitys.ProvaDettaglioPolizza;
import com.salvio.services.ProvaRicercaAnagraficheDiPolizzaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvaRicercaAnagraficheDiPolizzaControllerTest {

  @InjectMocks
  private ProvaRicercaAnagraficheDiPolizzaController provaRicercaAnagraficheDiPolizzaController;

  @Mock
  private ProvaRicercaAnagraficheDiPolizzaService provaRicercaAnagraficheDiPolizzaService;


  @Test
  public void controlloPathControllerOk(){


    String codiceFiscale="1234567890123456";
    List<ProvaDettaglioPolizza> listaPolizze = getListaDettaglioPolizze(codiceFiscale);
    when(provaRicercaAnagraficheDiPolizzaService.ottieniPolizzeCollegateACodiceFiscaleFornito(codiceFiscale)).thenReturn(listaPolizze);
    List <ProvaDettaglioPolizza> provaDettaglioPolizzaList =provaRicercaAnagraficheDiPolizzaController.execute(codiceFiscale);


    Assertions.assertEquals(Optional.of(1),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(0).getPolizzaProva().getNumeroPolizza()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(0).getContraente().getCodiceFiscale()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(0).getAssicurato().getCodiceFiscale()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(0).getBeneficiario().getCodiceFiscale()));

    Assertions.assertEquals(Optional.of(2),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(1).getPolizzaProva().getNumeroPolizza()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(1).getContraente().getCodiceFiscale()));
    Assertions.assertNotEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(1).getAssicurato().getCodiceFiscale()));
    Assertions.assertNotEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(provaDettaglioPolizzaList.get(1).getBeneficiario().getCodiceFiscale()));

  }




  private List<ProvaDettaglioPolizza> getListaDettaglioPolizze(String codiceFiscale) {

    List<ProvaDettaglioPolizza> dettaglioPolizzaList= new ArrayList<>();

    dettaglioPolizzaList.add(new ProvaDettaglioPolizza(
        new PolizzaProva(1,9999,9999,9999),
        new AnagraficaProva(9999,"Mario","Rossi",codiceFiscale),
        new AnagraficaProva(9999,"Mario","Rossi",codiceFiscale),
        new AnagraficaProva(9999,"Mario","Rossi",codiceFiscale)
    ));

    dettaglioPolizzaList.add(new ProvaDettaglioPolizza(
        new PolizzaProva(2,9999,2222,8888),
        new AnagraficaProva(9999,"Mario","Rossi",codiceFiscale),
        new AnagraficaProva(2222,"Gennaro","Esposito","gnnsps13d14i445v"),
        new AnagraficaProva(8888,"Pippo","Pluto","pppplt13d14i445v")

    ));

    return dettaglioPolizzaList;
  }


}
