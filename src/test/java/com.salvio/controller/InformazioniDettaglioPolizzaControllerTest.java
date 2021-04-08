package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.Polizza;
import com.salvio.entitys.PolizzaProva;
import com.salvio.services.InformazioniPolizzaService;
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


  @Test
  public void controlloExecuteController(){

    String codiceFiscale="123456";
    List<DettaglioPolizzaProva> listaExpected= new ArrayList<>();
    listaExpected.add(new DettaglioPolizzaProva(new PolizzaProva(1,9999,9999,9999),null,null,null));
    when(informazioniPolizzaService.estraiEProcessaInformazioni(codiceFiscale)).thenReturn(listaExpected);


    List<DettaglioPolizzaProva> lista= informazioniPolizzaController.gestisciEndpoint(codiceFiscale);

    Assertions.assertThat(lista.get(0).getPolizza().getNumeroPolizza()).isEqualTo(listaExpected.get(0).getPolizza().getNumeroPolizza());
    Assertions.assertThat(lista.get(0).getPolizza().getIdContraente()).isEqualTo(listaExpected.get(0).getPolizza().getIdContraente());
    Assertions.assertThat(lista.get(0).getPolizza().getIdAssicurato()).isEqualTo(listaExpected.get(0).getPolizza().getIdAssicurato());
    Assertions.assertThat(lista.get(0).getPolizza().getIdBeneficiario()).isEqualTo(listaExpected.get(0).getPolizza().getIdBeneficiario());


  }





}
