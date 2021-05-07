package com.arca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticheAccessoBS {

    private String data;
    private String bancaSso;
    private Integer conteggioAccessi;


    public static void writeStatisticheAccesso(StatisticheAccessoBS statisticheAccesso, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(statisticheAccesso.getData());

        cell = row.createCell(1);
        cell.setCellValue(statisticheAccesso.getBancaSso());

        cell = row.createCell(2);
        cell.setCellValue(statisticheAccesso.getConteggioAccessi());
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
