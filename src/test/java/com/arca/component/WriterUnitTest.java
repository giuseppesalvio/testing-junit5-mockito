package com.arca.component;

import static com.arca.component.CreaExcel.CONTEGGIO_ACCESSI;
import static com.arca.component.Writer.NOME_FILE_EXCEL;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.apache.commons.io.FileUtils.directoryContains;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.StartApplication;
import com.arca.entity.ConteggioAccessi;
import com.arca.repository.StatisticheAccessoRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
class WriterUnitTest {

  @InjectMocks
  Writer writer;

  @Mock
  StatisticheAccessoRepository statisticheAccessoRepository;

  @Test
  public void scrivoSheetConteggioAccessi(){

    List<ConteggioAccessi> valori = asList(new ConteggioAccessi("01/01/2001",1,2));

    when(statisticheAccessoRepository.estraiConteggioAccessiUnivoci()).thenReturn(valori);

    writer.write();

    verify(writer).write(CONTEGGIO_ACCESSI,valori);

  }




}
