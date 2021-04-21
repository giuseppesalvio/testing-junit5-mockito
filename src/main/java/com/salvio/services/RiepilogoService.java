package com.salvio.services;

import com.salvio.entitys.InfoPolizzaCompletaRiepilogo;
import com.salvio.entitys.RiepilogoPolizzaCompleta;
import com.salvio.entitys.RiepilogoAnagrafica;
import com.salvio.entitys.RiepilogoAutomobile;
import com.salvio.repository.RiepilogoAnagraficaRepository;
import com.salvio.repository.RiepilogoAutomobileRepository;
import com.salvio.repository.RiepilogoPolizzaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RiepilogoService {


  private final RiepilogoAutomobileRepository riepilogoAutomobileRepository;
  private final RiepilogoAnagraficaRepository riepilogoAnagraficaRepository;
  private final RiepilogoPolizzaRepository riepilogoPolizzaRepository;

  public RiepilogoService(RiepilogoAutomobileRepository riepilogoAutomobileRepository,
      RiepilogoAnagraficaRepository riepilogoAnagraficaRepository,
      RiepilogoPolizzaRepository riepilogoPolizzaRepository) {
    this.riepilogoAutomobileRepository = riepilogoAutomobileRepository;
    this.riepilogoAnagraficaRepository = riepilogoAnagraficaRepository;
    this.riepilogoPolizzaRepository = riepilogoPolizzaRepository;
  }

  public InfoPolizzaCompletaRiepilogo costruisciRispostaService(String infoSinistro) {

    String targaDaValidare="CE653TN";

    RiepilogoAutomobile riepilogoAutomobile= riepilogoAutomobileRepository.getInfoAutomobileBy(targaDaValidare);
    RiepilogoAnagrafica riepilogoAnagrafica= riepilogoAnagraficaRepository.getInfoAnagraficaBy(riepilogoAutomobile.getCodiceFiscaleProprietario());
    List<RiepilogoPolizzaCompleta> listaPolizze=riepilogoPolizzaRepository.getInfoPolizzeBy(riepilogoAnagrafica.getIdAnagrafica());

    Double totalePremio=500.00;

    InfoPolizzaCompletaRiepilogo rispostaCompleta= InfoPolizzaCompletaRiepilogo.builder().listaPolizze(listaPolizze).totaleImportoDaVersare(totalePremio).build();
    return rispostaCompleta;
  }
}
