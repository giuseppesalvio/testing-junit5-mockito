package com.roberto.service;

import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.roberto.entitys.AnagraficaEstesa;
import com.roberto.entitys.Automobile;
import com.roberto.entitys.InfoPolizzaEstesa;
import com.roberto.entitys.PolizzaEstesa;
import com.roberto.entitys.Sinistro;
import com.roberto.repository.AnagraficaEstesaRepository;
import com.roberto.repository.AutomobileRepository;
import com.roberto.repository.PolizzaEstesaRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.roberto.services.ValiditàSinistroService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompatibilitaSinistroServiceUnitTest {

  @InjectMocks
  private ValiditàSinistroService validitàSinistroService;

  @Mock
  private PolizzaEstesaRepository polizzaEstesaRepository;
  @Mock
  private AutomobileRepository automobileRepository;
  @Mock
  private AnagraficaEstesaRepository anagraficaEstesaRepository;

  @Test
  public void controlloValiditaSinistroServiceTest() {
    String infoSinistro = "";
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA
      jsonObject.put("dataSinistro", "2021-04-12");


      infoSinistro = jsonObject.toString();
    }
    catch(JSONException e){
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }


    String targaDaVerificare = "CE653TN";
    String dataProxQuietanz = "2021-06-01";
    boolean flag=false;
    try {
      Gson gson = new Gson();
      Sinistro jsonSinistro = gson.fromJson(infoSinistro, Sinistro.class);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date dataSinistro = sdf.parse(jsonSinistro.getDataSinistro());
      Date dataValiditaPolizza = sdf.parse(dataProxQuietanz);
      if(dataSinistro.before(dataValiditaPolizza)){
        flag=true;
      }

    }
    catch(ParseException e ){System.out.println("DATA INVALIDA");}



    Automobile automobileProva = Automobile.builder().numeroTarga("CE653TN").codiceFiscaleProprietario("cstmnl")
        .P_IvaAssicurazioneAssociata("01333550323").numeroPolizzaAssociata(1).build();
    PolizzaEstesa polizzaEstesaProva = PolizzaEstesa.builder().numeroPolizza(1).idContraente(9797).idAssicurato(9797)
        .idBeneficiario(9797).dataProxQuietanzamento("2021-06-01").importoQuietanzamento(500.00).build();

    AnagraficaEstesa anagraficaEstesa = AnagraficaEstesa.builder().idAnagrafica(9797).nome("emanuele").cognome("castagnaro")
        .codiceFiscale("cstmnl").build();

    List<PolizzaEstesa> listaPolizzeAssociateProva = new ArrayList<>();

    listaPolizzeAssociateProva.add(PolizzaEstesa.builder().numeroPolizza(1).idContraente(9797).idAssicurato(9797)
        .idBeneficiario(9797).dataProxQuietanzamento("2021-06-01").importoQuietanzamento(500.00).build());

    listaPolizzeAssociateProva.add(PolizzaEstesa.builder().numeroPolizza(5).idContraente(9797).idAssicurato(2222)
        .idBeneficiario(9999).dataProxQuietanzamento("2021-06-01").importoQuietanzamento(500.00).build());

    int totalePremioDaVersare=1000;

    when(automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(targaDaVerificare)).thenReturn(automobileProva);

    when(polizzaEstesaRepository.estraiDatiPolizzaFornita(automobileProva.getNumeroPolizzaAssociata()))
        .thenReturn(polizzaEstesaProva);

    when(anagraficaEstesaRepository
        .estraiDatiAnagraficaDaCodiceFiscaleFornito(automobileProva.getCodiceFiscaleProprietario()))
        .thenReturn(anagraficaEstesa);

    when(polizzaEstesaRepository.ottieniPolizzeConContraenteIdFornito(anagraficaEstesa.getIdAnagrafica()))
        .thenReturn(listaPolizzeAssociateProva);



    InfoPolizzaEstesa infoPolizzaEstesa = validitàSinistroService.executeOperazioniService(infoSinistro);



   Assertions.assertThat(infoPolizzaEstesa.getTotalePremioDaVersare()).isEqualTo(totalePremioDaVersare);
   Assertions.assertThat(infoPolizzaEstesa.getListaPolizzeCollegate().size()).isEqualTo(2);

  }

}
