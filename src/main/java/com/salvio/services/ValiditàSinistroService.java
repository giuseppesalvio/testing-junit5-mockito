package com.salvio.services;

import com.google.gson.Gson;
import com.salvio.entitys.AnagraficaEstesa;
import com.salvio.entitys.Automobile;
import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.entitys.PolizzaEstesa;
import com.salvio.entitys.Sinistro;
import com.salvio.repository.AnagraficaEstesaRepository;
import com.salvio.repository.AutomobileRepository;
import com.salvio.repository.PolizzaEstesaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ValiditàSinistroService {

  private AutomobileRepository automobileRepository;
  private PolizzaEstesaRepository polizzaEstesaRepository;
  private AnagraficaEstesaRepository anagraficaEstesaRepository;

  public ValiditàSinistroService(AutomobileRepository automobileRepository,
      PolizzaEstesaRepository polizzaEstesaRepository, AnagraficaEstesaRepository anagraficaEstesaRepository) {
    this.automobileRepository = automobileRepository;
    this.polizzaEstesaRepository = polizzaEstesaRepository;
    this.anagraficaEstesaRepository = anagraficaEstesaRepository;
  }

  public InfoPolizzaEstesa executeOperazioniService(String infoSinistro) {

    String p_iva="CE653TN";
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



     AnagraficaEstesa anagraficaEstesa= anagraficaEstesaRepository.estraiDatiAnagraficaDaCodiceFiscaleFornito(automobile.getCodiceFiscaleProprietario());

     List<PolizzaEstesa> listaPolizzeCollegateAIdAnagrafica =polizzaEstesaRepository.ottieniPolizzeConContraenteIdFornito(anagraficaEstesa.getIdAnagrafica());

     int totalePremio=0;

     for (PolizzaEstesa polizza: listaPolizzeCollegateAIdAnagrafica ){

       totalePremio+=polizza.getImportoQuietanzamento();


     }

     infoPolizzaEstesaOttenuta=InfoPolizzaEstesa.builder().listaPolizzeCollegate(listaPolizzeCollegateAIdAnagrafica).totalePremioDaVersare(totalePremio).build();


    }
    catch (NullPointerException e){
      System.out.println("le targhe fornite non sono associate a nessuna polizza valida della nostra compagnia");
      return null;
    }

    return infoPolizzaEstesaOttenuta;
  }
}
