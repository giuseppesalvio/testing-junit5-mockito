package com.arca.component;

import com.arca.entity.ConteggioAccessi;
import com.arca.repository.StatisticheAccessoRepository;
import java.util.List;

public class CreaExcel {

  public static final String CONTEGGIO_ACCESSI = "ConteggioAccessi";

  private final Writer writer;
  private final StatisticheAccessoRepository statisticheAccessoRepository;

  public CreaExcel(Writer writer, StatisticheAccessoRepository statisticheAccessoRepository) {
    this.writer = writer;
    this.statisticheAccessoRepository = statisticheAccessoRepository;
  }


  public void crea() {
    List<ConteggioAccessi> listaConteggioAccessi = statisticheAccessoRepository.estraiConteggioAccessiUnivoci();
    writer.write(CONTEGGIO_ACCESSI,listaConteggioAccessi);
  }
}
