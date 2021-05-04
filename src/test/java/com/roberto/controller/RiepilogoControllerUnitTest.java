package com.roberto.controller;

import com.roberto.entitys.InfoPolizzaCompletaRiepilogo;
import com.roberto.entitys.RiepilogoPolizzaCompleta;
import com.roberto.services.RiepilogoService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RiepilogoControllerUnitTest {


  @InjectMocks
  private RiepilogoController riepilogoController;

  @Mock
  private RiepilogoService riepilogoService;


  @Test
  public void verificaTestControllerOk() {

    String infoSinistro = "";

    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");
      jsonObject.put("assicurazioneB", "03740811207");
      jsonObject.put("dataSinistro", "2021-04-20");

      infoSinistro = jsonObject.toString();

    } catch (JSONException e) {
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }

    List<RiepilogoPolizzaCompleta> listaProva = new ArrayList<>();
    listaProva.add(RiepilogoPolizzaCompleta.builder().numeroPolizza(1).idContraente(9797).idAssicurato(2222).idBeneficiario(8888)
        .dataProxQuitanzamento("2021-06-01").importoQuietanzamento(500.00).build());

    Double totaleImportoDaVersare = 500.00;

    InfoPolizzaCompletaRiepilogo infoPolizzaCompletaRiepilogoProva = InfoPolizzaCompletaRiepilogo.builder().listaPolizze(listaProva)
        .totaleImportoDaVersare(totaleImportoDaVersare).build();

    when(riepilogoService.costruisciRispostaService(infoSinistro)).thenReturn(infoPolizzaCompletaRiepilogoProva);

    InfoPolizzaCompletaRiepilogo infoPolizzaCompletaRiepilogo = riepilogoController.checkAndReturnLinkedList(infoSinistro);

    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getListaPolizze().size()).isEqualTo(1);
    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getTotaleImportoDaVersare()).isEqualTo(500.00);


  }

}
