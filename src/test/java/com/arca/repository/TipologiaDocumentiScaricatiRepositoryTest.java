package com.arca.repository;

import com.StartApplication;
import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.DocumentoScaricatoTotalmente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest(classes = {StartApplication.class})
public class TipologiaDocumentiScaricatiRepositoryTest {

  @Autowired
  private TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaConteggioGiornalmenteOk(){

    jdbcTemplate.update("insert into STATISTICHE(CF,AZIONE,TMST_DOWNLOAD,TARGA,POLIZZA) values ('VSNLCA66B58L781P','DOWNLOAD_DFF',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),null,null)");
    jdbcTemplate.update("insert into STATISTICHE(CF,AZIONE,TMST_DOWNLOAD,TARGA,POLIZZA) values ('VSNLCA66B58L781P','DOWNLOAD_DFF',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),null,null)");

    DocumentoScaricatoGiornalmente expected= new DocumentoScaricatoGiornalmente("2021/04/29","DOWNLOAD_DFF",1);

    List<DocumentoScaricatoGiornalmente> listaOttenuta= tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiGiornalmente();

    Assertions.assertThat(listaOttenuta.get(0)).isEqualToComparingFieldByField(expected);
    Assertions.assertThat(listaOttenuta.size()).isEqualTo(2);
  }

  @Test
  public void verificaConteggioTotalmenteOk(){

    jdbcTemplate.update("insert into STATISTICHE(CF,AZIONE,TMST_DOWNLOAD,TARGA,POLIZZA) values ('VSNLCA66B58L781P','DOWNLOAD_DFF',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),null,null)");

    DocumentoScaricatoTotalmente expected= new DocumentoScaricatoTotalmente("DOWNLOAD_DFF", 1);

   List<DocumentoScaricatoTotalmente> listaOttenuta= tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiTotalmente();

    Assertions.assertThat(listaOttenuta.get(0)).isEqualToComparingFieldByField(expected);
  }


}
