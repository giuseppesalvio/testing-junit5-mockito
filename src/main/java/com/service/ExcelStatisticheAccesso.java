package com.service;

import static com.entity.StatisticheAccesso.writeHeaderStatisticheAccesso;
import static com.entity.StatisticheAccesso.writeStatisticheAccesso;

import com.arca.repository.StatisticheAccessoRepository;
import com.entity.StatisticheAccesso;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelStatisticheAccesso
{
    final StatisticheAccessoRepository statisticheAccessoRepository;

    public ExcelStatisticheAccesso(StatisticheAccessoRepository statisticheAccessoRepository) {
        this.statisticheAccessoRepository = statisticheAccessoRepository;
    }

    public void writeExcel(List<StatisticheAccesso> list, String excelFilePath) throws IOException
    {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        Row rowHeader = sheet.createRow(0);
        writeHeaderStatisticheAccesso(rowHeader);

        for (StatisticheAccesso temp : list) {
            Row row = sheet.createRow(++rowCount);
            writeStatisticheAccesso(temp, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    public void execute()
    {
        ExcelStatisticheAccesso excelStatisticheAccesso = new ExcelStatisticheAccesso(statisticheAccessoRepository);

        List<StatisticheAccesso> list = statisticheAccessoRepository.retrive();


        String excelFilePath = "NiceJavaBooks.xls";

        try {
            excelStatisticheAccesso.writeExcel(list, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
