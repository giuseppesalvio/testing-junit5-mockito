package com.entity;

import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class StatisticheAccesso {
    private String data;
    private String bancaSso;
    private Integer conteggioAccessi;

    public static void writeStatisticheAccesso(StatisticheAccesso aBook, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(aBook.getData());

        cell = row.createCell(1);
        cell.setCellValue(aBook.getBancaSso());

        cell = row.createCell(2);
        cell.setCellValue(aBook.getConteggioAccessi());
    }

    public static void writeHeaderStatisticheAccesso(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("data");

        cell = row.createCell(1);
        cell.setCellValue("bancaSso");

        cell = row.createCell(2);
        cell.setCellValue("conteggioAccessi");
    }
}
