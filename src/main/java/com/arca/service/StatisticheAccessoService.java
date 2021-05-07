package com.arca.service;

import com.arca.entity.ConteggioAccessi;
import com.arca.entity.StatisticheAccesso;
import com.arca.model.ConteggioAccessiBS;
import com.arca.model.StatisticheAccessoBS;
import com.arca.repository.StatisticheAccessoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StatisticheAccessoService {

  private final StatisticheAccessoRepository statisticheAccessoRepository;

  public StatisticheAccessoService(StatisticheAccessoRepository statisticheAccessoRepository) {
    this.statisticheAccessoRepository = statisticheAccessoRepository;
  }

  public List<StatisticheAccessoBS> getStatisticheAccessoSso() {


    List<StatisticheAccesso> listaDB= statisticheAccessoRepository.retriveAccessiSSO();
    List<StatisticheAccessoBS> listaBS= new ArrayList<>();

    for (StatisticheAccesso stat : listaDB ){

      listaBS.add(new StatisticheAccessoBS(stat.getData(), stat.getBancaSso(), stat.getConteggioAccessi()));

    }
     return listaBS;
  }

  public List<ConteggioAccessiBS> getNumeroAccessi() {

    List<ConteggioAccessi> listaDB =statisticheAccessoRepository.estraiConteggioAccessiUnivoci();
    List<ConteggioAccessiBS> listaBS = new ArrayList<>();

    for (ConteggioAccessi elem : listaDB ){

      listaBS.add(mapToBs(elem));
    }
    return listaBS;

  }

  private ConteggioAccessiBS mapToBs(ConteggioAccessi conteggioAccessi)
  {
    ConteggioAccessiBS result  =new ConteggioAccessiBS(conteggioAccessi.getData(), conteggioAccessi.getAccessiUnivociGG(), conteggioAccessi.getAccessiNonUnivociGG());
    return result;
  }
}
