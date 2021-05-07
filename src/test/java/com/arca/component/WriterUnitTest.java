package com.arca.component;

import com.arca.repository.StatisticheAccessoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class WriterUnitTest {

  @InjectMocks
  Writer writer;

  @Mock
  ConteggioAccessiSheet conteggioAccessiSheet;

  @Mock
  StatisticheAccessoSheet statisticheAccessoSheet;

  @Mock
  DocumentoScaricatoGiornalmenteSheet documentoScaricatoGiornalmenteSheet;

  @Mock
  DocumentoScaricatoTotalmenteSheet documentoScaricatoTotalmenteSheet;

  @Test
  public void scrivoSheetConteggioAccessi(){

    writer.write();

    verify(conteggioAccessiSheet).creaSheet(any());
    verify(statisticheAccessoSheet).creaSheet(any());
    verify(documentoScaricatoGiornalmenteSheet).creaSheet(any());
    verify(documentoScaricatoTotalmenteSheet).creaSheet(any());
  }

}
