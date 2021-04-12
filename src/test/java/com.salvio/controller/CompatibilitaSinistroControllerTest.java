package com.salvio.controller;

import static org.mockito.Mockito.when;

import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.entitys.PolizzaEstesa;
import com.salvio.services.ValiditàSinistroService;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompatibilitaSinistroControllerTest {

  @InjectMocks
  private ValiditaSinistroController compatibilitàSinistroController;

  @Mock
  private ValiditàSinistroService validitàSinistroService;

  @Test
  public void controlloValiditaSinistroControllerTest() {
    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA
      jsonObject.put("dataSinistro", "12-04-2021");


      infoSinistro = jsonObject.toString();
    }
    catch(JSONException e){
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }
    List<PolizzaEstesa> listaPolizzeEsteseProva = new ArrayList<>();
    listaPolizzeEsteseProva.add(
        PolizzaEstesa.builder().numeroPolizza(1).idContraente(9999).idAssicurato(9999).idBeneficiario(9999)
            .dataProxQuietanzamento("01/06/2021").importoQuietanzamento(500.01).build()
    );
    listaPolizzeEsteseProva.add(
        PolizzaEstesa.builder().numeroPolizza(2).idContraente(9999).idAssicurato(2222).idBeneficiario(2222)
            .dataProxQuietanzamento("01/06/2021").importoQuietanzamento(499.99).build()
    );

    int totaleDaVersareProva = 1000;


    InfoPolizzaEstesa infoPolizzaEstesaProva = InfoPolizzaEstesa.builder().listaPolizzeCollegate(listaPolizzeEsteseProva)
        .totalePremioDaVersare(totaleDaVersareProva).build();

    when(validitàSinistroService.executeOperazioniService(infoSinistro)).thenReturn(infoPolizzaEstesaProva);


    InfoPolizzaEstesa infoPolizzaEstesa = compatibilitàSinistroController.gestisciEndPoint(infoSinistro);



    Assertions.assertThat(infoPolizzaEstesa.getListaPolizzeCollegate().size()).isEqualTo(2);
    Assertions.assertThat(infoPolizzaEstesa.getTotalePremioDaVersare()).isEqualTo(1000);



  }

}
