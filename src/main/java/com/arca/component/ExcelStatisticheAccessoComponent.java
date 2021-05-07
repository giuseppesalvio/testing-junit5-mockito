package com.arca.component;
import com.arca.entity.StatisticheAccesso;
import com.arca.model.StatisticheAccessoBS;
import com.arca.service.StatisticheAccessoService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelStatisticheAccessoComponent
{
    final StatisticheAccessoService statisticheAccessoService;

    public ExcelStatisticheAccessoComponent(StatisticheAccessoService statisticheAccessoService) {
        this.statisticheAccessoService = statisticheAccessoService;
    }


    public void writeExcel(List<StatisticheAccessoBS> list, String excelFilePath) throws IOException
    {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        Row rowHeader = sheet.createRow(0);
       this.writeHeaderStatisticheAccesso(rowHeader);

        for (StatisticheAccessoBS temp : list) {
            Row row = sheet.createRow(++rowCount);
            this.writeStatisticheAccesso(temp, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }
    public void writeStatisticheAccesso(StatisticheAccessoBS statisticheAccesso, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(statisticheAccesso.getData());

        cell = row.createCell(1);
        cell.setCellValue(statisticheAccesso.getBancaSso());

        cell = row.createCell(2);
        cell.setCellValue(statisticheAccesso.getConteggioAccessi());
    }

    public void writeHeaderStatisticheAccesso(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("data");

        cell = row.createCell(1);
        cell.setCellValue("bancaSso");

        cell = row.createCell(2);
        cell.setCellValue("conteggioAccessi");
    }

    public void execute()
    {
        List<StatisticheAccessoBS>list = statisticheAccessoService.getStatisticheAccessoSso();


        String excelFilePath = "NiceJavaBooks.xls";

        try {
            this.writeExcel(list, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
