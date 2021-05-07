package com.arca.component;

import static com.arca.component.CreaExcel.CONTEGGIO_ACCESSI;
import static com.arca.component.Writer.NOME_FILE_EXCEL;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.apache.commons.io.FileUtils.directoryContains;
import static org.assertj.core.api.Assertions.assertThat;

import com.StartApplication;
import com.arca.entity.ConteggioAccessi;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = {StartApplication.class})
class WriterTest {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public static final String DIR_CORRENTE = "./";

  @Test
  public void scriveSheetConIntestazione() throws IOException {
    File directory = new File(DIR_CORRENTE);
    File child = new File(NOME_FILE_EXCEL);
    cancellaFileSeEsisteGia(directory, child);

    Writer writer = new Writer();
    writer.write(CONTEGGIO_ACCESSI, emptyList());

    assertThat(directoryContains(directory,child)).isTrue();
  }

  @Test
  public void scriveSheetConIntestazioneAndDati() throws IOException {


    File directory = new File(DIR_CORRENTE);
    File child = new File(NOME_FILE_EXCEL);
    cancellaFileSeEsisteGia(directory, child);


    Writer writer = new Writer();
    List<ConteggioAccessi> lista = asList(
        new ConteggioAccessi("02/02/2001",1,11),
        new ConteggioAccessi("02/02/2002",2,22),
        new ConteggioAccessi("02/02/2003",3,33),
        new ConteggioAccessi("02/02/2004",4,44)
    );

    writer.write(CONTEGGIO_ACCESSI, lista);

    assertThat(directoryContains(directory,child)).isTrue();
  }

  private void cancellaFileSeEsisteGia(File directory, File child) throws IOException {
    boolean fileExcel = directoryContains(directory, child);
    if (fileExcel) {
      FileUtils.forceDelete(new File(DIR_CORRENTE + NOME_FILE_EXCEL));
    }
  }




}
