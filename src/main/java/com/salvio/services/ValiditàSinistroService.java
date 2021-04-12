package com.salvio.services;

import com.salvio.entitys.AnagraficaEstesa;
import com.salvio.entitys.Automobile;
import com.salvio.entitys.InfoPolizzaEstesa;
import com.salvio.entitys.PolizzaEstesa;
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

  public InfoPolizzaEstesa executeOperazioniService(String infoSinistro) {
    try {
      Automobile automobile = automobileRepository.verificaValiditaSinistroEdEstraiNumeroPolizza(infoSinistro);

      PolizzaEstesa polizzaEstesa = polizzaEstesaRepository.estraiDatiPolizzaFornita(automobile.getNumeroPolizzaAssociata());

     AnagraficaEstesa anagraficaEstesa= anagraficaEstesaRepository.estraiDatiAnagraficaDaCodiceFiscaleFornito(automobile.getCodiceFiscaleProprietario());

     List<PolizzaEstesa> listaPolizzeCollegateAIdAnagrafica =polizzaEstesaRepository.ottieniPolizzeConContraenteIdFornito(anagraficaEstesa.getIdAnagrafica());

    }
    catch (NullPointerException e){
      return null;}

    return null;
  }
}
