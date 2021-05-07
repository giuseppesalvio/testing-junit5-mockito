package com.arca.entity;

import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class StatisticheAccesso {
    private String data;
    private String bancaSso;
    private Integer conteggioAccessi;

    public static void writeRow(StatisticheAccesso temp, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(temp.getData());

        cell = row.createCell(1);
        cell.setCellValue(temp.getBancaSso());

        cell = row.createCell(2);
        cell.setCellValue(temp.getConteggioAccessi());
    }

    public static void writeHeader(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("data");

        cell = row.createCell(1);
        cell.setCellValue("bancaSso");

        cell = row.createCell(2);
        cell.setCellValue("conteggioAccessi");
    }
}
