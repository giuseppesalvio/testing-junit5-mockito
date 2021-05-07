package com.arca.component;

import com.arca.repository.StatisticheAccessoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


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

    creaExcel.crea();

    verify(writer).write();

  }

}
