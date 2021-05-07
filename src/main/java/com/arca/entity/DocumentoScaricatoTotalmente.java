package com.arca.entity;

import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class DocumentoScaricatoTotalmente {
  private String tipoDocumento;
  private Integer numeroDownload;

  public static void writeRow(DocumentoScaricatoTotalmente temp, Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue(temp.getTipoDocumento());

    cell = row.createCell(1);
    cell.setCellValue(temp.getNumeroDownload());

  }

  public static void writeHeader(Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue("tipoDocumento");

    cell = row.createCell(1);
    cell.setCellValue("numeroDownload");

  }
}
