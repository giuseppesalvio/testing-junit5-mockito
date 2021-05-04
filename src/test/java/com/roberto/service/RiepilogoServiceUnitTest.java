package com.roberto.service;

import static org.mockito.Mockito.when;

import com.roberto.entitys.InfoPolizzaCompletaRiepilogo;
import com.roberto.entitys.RiepilogoPolizzaCompleta;
import com.roberto.entitys.RiepilogoAnagrafica;
import com.roberto.entitys.RiepilogoAutomobile;
import com.roberto.repository.RiepilogoAnagraficaRepository;
import com.roberto.repository.RiepilogoAutomobileRepository;
import com.roberto.repository.RiepilogoPolizzaRepository;
import java.util.ArrayList;
import java.util.List;

import com.roberto.services.RiepilogoService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiepilogoServiceUnitTest {

  @InjectMocks
  private RiepilogoService riepilogoService;

  @Mock
  private RiepilogoAnagraficaRepository riepilogoAnagraficaRepository;

  @Mock
  private RiepilogoPolizzaRepository riepilogoPolizzaRepository;

  @Mock
  private RiepilogoAutomobileRepository riepilogoAutomobileRepository;

  @Test
  public void verificaTestServiceOk() {

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

    String targaDaValidare = "CE653TN";
    RiepilogoAutomobile riepilogoAutomobileProva = RiepilogoAutomobile.builder().numeroTarga("CE653TN").codiceFiscaleProprietario("cstmnl")
        .codiceAssicurazione("01333550323").numeroPolizzaAssociata(1).build();


    RiepilogoAnagrafica riepilogoAnagraficaProva = RiepilogoAnagrafica.builder().build();

    Integer idAnagrafica = 9797;
    List<RiepilogoPolizzaCompleta> listaProva = new ArrayList<>();
    listaProva.add(RiepilogoPolizzaCompleta.builder().numeroPolizza(1).idContraente(9797).idAssicurato(2222).idBeneficiario(8888)
        .dataProxQuitanzamento("2021-06-01").importoQuietanzamento(500.00).build());



    when(riepilogoAutomobileRepository.getInfoAutomobileBy(targaDaValidare)).thenReturn(riepilogoAutomobileProva);
    when(riepilogoAnagraficaRepository.getInfoAnagraficaBy(riepilogoAutomobileProva.getCodiceFiscaleProprietario())).thenReturn(riepilogoAnagraficaProva);
    when(riepilogoPolizzaRepository.getInfoPolizzeBy(riepilogoAnagraficaProva.getIdAnagrafica())).thenReturn(listaProva);


    InfoPolizzaCompletaRiepilogo infoPolizzaCompletaRiepilogo = riepilogoService.costruisciRispostaService(infoSinistro);

    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getListaPolizze().size()).isEqualTo(1);
    Assertions.assertThat(infoPolizzaCompletaRiepilogo.getTotaleImportoDaVersare()).isEqualTo(500.00);

  }

}
