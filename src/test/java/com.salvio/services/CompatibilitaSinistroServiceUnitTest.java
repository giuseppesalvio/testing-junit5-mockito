package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaEstesa;
import com.salvio.entitys.Automobile;
import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.entitys.PolizzaEstesa;
import com.salvio.repository.AnagraficaEstesaRepository;
import com.salvio.repository.AutomobileRepository;
import com.salvio.repository.PolizzaEstesaRepository;
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
      JSONArray jsonArray = new JSONArray();
      JSONObject item = new JSONObject();
      jsonObject.put("targaA", "CE653TN");
      jsonObject.put("targaB", "FL041PB");
      jsonObject.put("assicurazioneA", "01333550323");     //P.IVA GENERALI
      jsonObject.put("assicurazioneB", "03740811207");     //P.IVA ARCA

      item.put("giorno", "12/04/2021");
      item.put("ora", "14:00:00");
      jsonArray.put(item);
      jsonObject.put("dataSinistro", jsonArray);
      infoSinistro = jsonObject.toString();
    } catch (JSONException e) {
      System.out.println("ERRORE SULLA COMPOSIZIONE JSON");
    }
    Automobile automobileProva = Automobile.builder().numeroTarga("CE653TN").codiceFiscaleProprietario("cstmnl")
        .P_IvaAssicurazioneAssociata("01333550323").numeroPolizzaAssociata(1).build();
    PolizzaEstesa polizzaEstesaProva = PolizzaEstesa.builder().numeroPolizza(1).idContraente(9797).idAssicurato(9797)
        .idBeneficiario(9797).dataProxQuietanzamento("01/06/2021").importoQuietanzamento(500.00).build();

    AnagraficaEstesa anagraficaEstesa = AnagraficaEstesa.builder().idAnagrafica(9797).nome("emanuele").cognome("castagnaro")
        .codiceFiscale("cstmnl").build();

    List<PolizzaEstesa> listaPolizzeAssociateProva = new ArrayList<>();

    listaPolizzeAssociateProva.add(PolizzaEstesa.builder().numeroPolizza(1).idContraente(9797).idAssicurato(9797)
        .idBeneficiario(9797).dataProxQuietanzamento("01/06/2021").importoQuietanzamento(500.00).build());

    listaPolizzeAssociateProva.add(PolizzaEstesa.builder().numeroPolizza(5).idContraente(9797).idAssicurato(2222)
        .idBeneficiario(9999).dataProxQuietanzamento("01/06/2021").importoQuietanzamento(500.00).build());

    when(automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(infoSinistro)).thenReturn(automobileProva);

    when(polizzaEstesaRepository.estraiDatiPolizzaFornita(automobileProva.getNumeroPolizzaAssociata()))
        .thenReturn(polizzaEstesaProva);

    when(anagraficaEstesaRepository
        .estraiDatiAnagraficaDaCodiceFiscaleFornito(automobileProva.getCodiceFiscaleProprietario()))
        .thenReturn(anagraficaEstesa);

    when(polizzaEstesaRepository.ottieniPolizzeConContraenteIdFornito(anagraficaEstesa.getIdAnagrafica()))
        .thenReturn(listaPolizzeAssociateProva);
    InfoPolizzaEstesa infoPolizzaEstesa = validitàSinistroService.executeOperazioniService(infoSinistro);


    //Assertions.assertThat(infoPolizzaEstesa.getTotalePremioDaVersare()).isEqualTo(1000);
    //Assertions.assertThat(infoPolizzaEstesa.getListaPolizzeCollegate().size()).isEqualTo(2);

  }

}
