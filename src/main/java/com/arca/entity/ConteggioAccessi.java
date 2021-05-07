package com.arca.entity;

import com.arca.model.StatisticheAccessoBS;
import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class ConteggioAccessi {

  private String data;
  private Integer accessiUnivociGG;
  private Integer accessiNonUnivociGG;



  public static void writeConteggioAccessi(ConteggioAccessi conteggioAccessi, Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue(conteggioAccessi.getData());

    cell = row.createCell(1);
    cell.setCellValue(conteggioAccessi.getAccessiUnivociGG());

    cell = row.createCell(2);
    cell.setCellValue(conteggioAccessi.getAccessiNonUnivociGG());
  }

  public static void writeHeaderConteggioAccessi(Row row) {
    Cell cell = row.createCell(0);
    cell.setCellValue("data");

    cell = row.createCell(1);
    cell.setCellValue("accessiUnivociGG");

    cell = row.createCell(2);
    cell.setCellValue("accessiNonUnivociGG");
  }

}
