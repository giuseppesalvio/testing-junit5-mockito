package com.arca.component;

import static com.arca.component.CreaExcel.CONTEGGIO_ACCESSI;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.arca.entity.ConteggioAccessi;
import com.arca.repository.StatisticheAccessoRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CreaExcelTest {

  @InjectMocks
  CreaExcel creaExcel;

  @Mock
  Writer writer;

  @Mock
  StatisticheAccessoRepository statisticheAccessoRepository;

  @Test
  public void scrivoSheetConteggioAccessi(){

    List<ConteggioAccessi> valori = asList(new ConteggioAccessi("01/01/2001",1,2));

    when(statisticheAccessoRepository.estraiConteggioAccessiUnivoci()).thenReturn(valori);

    creaExcel.crea();

    verify(writer).write(CONTEGGIO_ACCESSI,valori);

  }

}
