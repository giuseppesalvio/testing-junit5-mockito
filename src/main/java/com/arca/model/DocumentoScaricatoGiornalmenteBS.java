package com.arca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoScaricatoGiornalmenteBS {


  private String data;
  private String tipoDocumento;
  private Integer numeroDownload;
}
