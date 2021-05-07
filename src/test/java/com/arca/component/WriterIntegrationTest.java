package com.arca.component;

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
class WriterIntegrationTest {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  Writer writer;

  public static final String DIR_CORRENTE = "./";

  @Test
  public void scriveSheetConIntestazione() throws IOException {
    File directory = new File(DIR_CORRENTE);
    File child = new File(NOME_FILE_EXCEL);
    cancellaFileSeEsisteGia(directory, child);

    writer.write();

    assertThat(directoryContains(directory,child)).isTrue();
  }

  @Test
  public void scriveSheetConIntestazioneAndDati() throws IOException {

    File directory = new File(DIR_CORRENTE);
    File child = new File(NOME_FILE_EXCEL);
    cancellaFileSeEsisteGia(directory, child);

    jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
    jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

    writer.write();

    assertThat(directoryContains(directory,child)).isTrue();
  }

  @Test
  public void scrive2Sheet() throws IOException {

    File directory = new File(DIR_CORRENTE);
    File child = new File(NOME_FILE_EXCEL);
    cancellaFileSeEsisteGia(directory, child);

    jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
    jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

    writer.write();

    assertThat(directoryContains(directory,child)).isTrue();
  }







  private void cancellaFileSeEsisteGia(File directory, File child) throws IOException {
    boolean fileExcel = directoryContains(directory, child);
    if (fileExcel) {
      FileUtils.forceDelete(new File(DIR_CORRENTE + NOME_FILE_EXCEL));
    }
  }





}
