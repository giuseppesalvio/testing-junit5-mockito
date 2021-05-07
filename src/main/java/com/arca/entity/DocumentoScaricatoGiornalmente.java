package com.arca.entity;

import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class DocumentoScaricatoGiornalmente {
  private String data;
  private String tipoDocumento;
  private Integer numeroDownload;

  public static void writeRow(DocumentoScaricatoGiornalmente temp, Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue(temp.getData());

    cell = row.createCell(1);
    cell.setCellValue(temp.getTipoDocumento());

    cell = row.createCell(2);
    cell.setCellValue(temp.getNumeroDownload());
  }

  public static void writeHeader(Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue("data");

    cell = row.createCell(1);
    cell.setCellValue("tipoDocumento");

    cell = row.createCell(2);
    cell.setCellValue("numeroDownload");
  }
}
