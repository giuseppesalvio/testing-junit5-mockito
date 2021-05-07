package com.arca.component;

import static com.arca.component.CreaExcel.CONTEGGIO_ACCESSI;
import static com.arca.component.Writer.NOME_FILE_EXCEL;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class WriterTest {


@Autowired
Writer writer;


  @Test
  public void scriveSheetConIntestazione() throws IOException {
    File directory = new File("./");
    File child = new File(NOME_FILE_EXCEL);
    boolean fileExcel = FileUtils.directoryContains(directory,child);
    if(fileExcel) {
      FileUtils.forceDelete(new File("./" + NOME_FILE_EXCEL));
    }

    writer.write(CONTEGGIO_ACCESSI, emptyList());

    fileExcel = FileUtils.directoryContains(directory,child);

    assertThat(fileExcel).isTrue();
  }
}
