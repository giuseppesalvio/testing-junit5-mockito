package com.arca.entity;

import lombok.Value;

@Value
public class DocumentoScaricatoGiornalmente {
  private String data;
  private String tipoDocumento;
  private Integer numeroDownload;
}
