package com.arca.service;

import static org.mockito.Mockito.when;

import com.arca.entity.ConteggioAccessi;
import com.arca.entity.StatisticheAccesso;
import com.arca.model.ConteggioAccessiBS;
import com.arca.model.StatisticheAccessoBS;
import com.arca.repository.StatisticheAccessoRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class StatisticheAccessoServiceUnitTest {

  @InjectMocks
  private StatisticheAccessoService statisticheAccessoService;

  @Mock
  private StatisticheAccessoRepository statisticheAccessoRepository;


  @Test
  public void verificaServiceSS0Ok(){
    List<StatisticheAccesso> listaMock= new ArrayList<>();
    listaMock.add(new StatisticheAccesso("29/04/2021","Sondrio",1));
    listaMock.add(new StatisticheAccesso("28/04/2021","Sondrio",1));

    when(statisticheAccessoRepository.retriveAccessiSSO()).thenReturn(listaMock);

    List<StatisticheAccessoBS> statisticheAccessoSSO = statisticheAccessoService.getStatisticheAccessoSso();

    Assertions.assertThat(statisticheAccessoSSO.size()).isEqualTo(2);

  }

  @Test
  public void verificaNumeroAccessiOk(){

    List<ConteggioAccessi> listaMock= new ArrayList<>();
    listaMock.add(new ConteggioAccessi("29/04/2021",1,1));
    listaMock.add(new ConteggioAccessi("28/04/2021",1,2));

    when(statisticheAccessoRepository.estraiConteggioAccessiUnivoci()).thenReturn(listaMock);

    List<ConteggioAccessiBS> listaConteggioAccessiUnivocieNon =statisticheAccessoService.getNumeroAccessi();

    Assertions.assertThat(listaConteggioAccessiUnivocieNon.size()).isEqualTo(2);
  }


}
