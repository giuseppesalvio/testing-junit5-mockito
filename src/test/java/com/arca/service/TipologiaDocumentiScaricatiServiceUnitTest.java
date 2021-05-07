package com.arca.service;

import static org.mockito.Mockito.when;

import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.DocumentoScaricatoTotalmente;
import com.arca.model.DocumentoScaricatoGiornalmenteBS;
import com.arca.model.DocumentoScaricatoTotalmenteBS;
import com.arca.repository.TipologiaDocumentiScaricatiRepository;
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
public class TipologiaDocumentiScaricatiServiceUnitTest {


  @InjectMocks
  private TipologiaDocumentiScaricatiService tipologiaDocumentiScaricatiService;


  @Mock
  private TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository;

  @Test
  public void verificaDocumentiScaricatiServiceOk(){
    List<DocumentoScaricatoGiornalmente> listaMock =new ArrayList<>();
    listaMock.add(new DocumentoScaricatoGiornalmente("29/04/2021","DOWNLOAD_DFF",2));
    listaMock.add(new DocumentoScaricatoGiornalmente("28/04/2021","DOWNLOAD_DFF",1));
    when(tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiGiornalmente()).thenReturn(listaMock);
    List<DocumentoScaricatoGiornalmenteBS> lista =tipologiaDocumentiScaricatiService.getDocumentiScaricatiGiornalmente();

    Assertions.assertThat(lista.size()).isEqualTo(2);
    Assertions.assertThat(lista.get(0).getData()).isEqualTo("29/04/2021");
  }

  @Test
  public void verificaDocumentiScaricatiTotalmenteOk(){

    List<DocumentoScaricatoTotalmente> listaMock=new ArrayList<>();
    listaMock.add(new DocumentoScaricatoTotalmente("DOWNLOAD_DFF",1));
    listaMock.add(new DocumentoScaricatoTotalmente("DOWNLOAD_PDF",1));
    when(tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiTotalmente()).thenReturn(listaMock);

    List<DocumentoScaricatoTotalmenteBS> lista=tipologiaDocumentiScaricatiService.getDocumentiScaricatiTotalmente();

    Assertions.assertThat(lista.size()).isEqualTo(2);

  }

}
