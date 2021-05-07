package com.arca.service;

import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.DocumentoScaricatoTotalmente;
import com.arca.model.DocumentoScaricatoGiornalmenteBS;
import com.arca.model.DocumentoScaricatoTotalmenteBS;
import com.arca.repository.TipologiaDocumentiScaricatiRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TipologiaDocumentiScaricatiService {


  private final TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository;

  public TipologiaDocumentiScaricatiService(
      TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository) {
    this.tipologiaDocumentiScaricatiRepository = tipologiaDocumentiScaricatiRepository;
  }

  public List<DocumentoScaricatoGiornalmenteBS> getDocumentiScaricatiGiornalmente() {

    List<DocumentoScaricatoGiornalmente> listaDB= tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiGiornalmente();

    List<DocumentoScaricatoGiornalmenteBS> listaBS= new ArrayList<>();
    for (DocumentoScaricatoGiornalmente elem : listaDB ){

      listaBS.add(new DocumentoScaricatoGiornalmenteBS(elem.getData(), elem.getTipoDocumento(), elem.getNumeroDownload()));

    }
    return listaBS;
  }

  public List<DocumentoScaricatoTotalmenteBS> getDocumentiScaricatiTotalmente() {
    List<DocumentoScaricatoTotalmente> listaDB = tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiTotalmente();

    List<DocumentoScaricatoTotalmenteBS> listaBS = new ArrayList<>();

    for(DocumentoScaricatoTotalmente elem : listaDB ){

      listaBS.add(new DocumentoScaricatoTotalmenteBS(elem.getTipoDocumento(), elem.getNumeroDownload()));

    }
    return listaBS;
  }
}
