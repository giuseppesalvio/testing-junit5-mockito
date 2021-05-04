package com.roberto.services;

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
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ValiditàSinistroService {

  private final AutomobileRepository automobileRepository;
  private final PolizzaEstesaRepository polizzaEstesaRepository;
  private final AnagraficaEstesaRepository anagraficaEstesaRepository;

  public ValiditàSinistroService(AutomobileRepository automobileRepository,
      PolizzaEstesaRepository polizzaEstesaRepository, AnagraficaEstesaRepository anagraficaEstesaRepository) {
    this.automobileRepository = automobileRepository;
    this.polizzaEstesaRepository = polizzaEstesaRepository;
    this.anagraficaEstesaRepository = anagraficaEstesaRepository;
  }


  public InfoPolizzaEstesa executeOperazioniService(String infoSinistro) {

    String p_iva="01333550323";
    Gson gson=new Gson();
    Sinistro jsonSinistro= gson.fromJson(infoSinistro,Sinistro.class);
    String targaDaVerificare=null;
    if(jsonSinistro.getAssicurazioneA().equals(p_iva))
    {
      targaDaVerificare= jsonSinistro.getTargaA();
    }
    if(jsonSinistro.getAssicurazioneB().equals(p_iva))
    {
      targaDaVerificare= jsonSinistro.getTargaB();
    }

    InfoPolizzaEstesa infoPolizzaEstesaOttenuta= null;

    try {
      Automobile automobile = automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(targaDaVerificare);

      PolizzaEstesa polizzaEstesa = polizzaEstesaRepository.estraiDatiPolizzaFornita(automobile.getNumeroPolizzaAssociata());

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date dataSinistro = sdf.parse(jsonSinistro.getDataSinistro());
      Date dataValiditaPolizza = sdf.parse(polizzaEstesa.getDataProxQuietanzamento());

      //curioso come col before che teoricamente quello corretto non vada, mentre con after funzioni...

      if (dataSinistro.before(dataValiditaPolizza)) {

        AnagraficaEstesa anagraficaEstesa = anagraficaEstesaRepository
            .estraiDatiAnagraficaDaCodiceFiscaleFornito(automobile.getCodiceFiscaleProprietario());

        List<PolizzaEstesa> listaPolizzeCollegateAIdAnagrafica = polizzaEstesaRepository
            .ottieniPolizzeConContraenteIdFornito(anagraficaEstesa.getIdAnagrafica());

        int totalePremio = 0;

        for (PolizzaEstesa polizza : listaPolizzeCollegateAIdAnagrafica) {

          totalePremio += polizza.getImportoQuietanzamento();

        }

        infoPolizzaEstesaOttenuta = InfoPolizzaEstesa.builder().listaPolizzeCollegate(listaPolizzeCollegateAIdAnagrafica)
            .totalePremioDaVersare(totalePremio).build();


      }
    }
    catch (NullPointerException | ParseException e){
      System.out.println("le targhe fornite non sono associate a nessuna polizza VALIDA della nostra compagnia");
      return null;
    }

    return infoPolizzaEstesaOttenuta;
  }
}
