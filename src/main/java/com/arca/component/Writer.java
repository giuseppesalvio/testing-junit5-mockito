package com.arca.component;

import static com.arca.component.CreaExcel.CONTEGGIO_ACCESSI;
import static com.arca.entity.ConteggioAccessi.writeConteggioAccessi;
import static com.arca.entity.ConteggioAccessi.writeHeaderConteggioAccessi;
import static com.arca.model.StatisticheAccessoBS.writeHeaderStatisticheAccesso;
import static com.arca.model.StatisticheAccessoBS.writeStatisticheAccesso;

import com.arca.entity.ConteggioAccessi;
import com.arca.model.StatisticheAccessoBS;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class Writer {

  public static final String NOME_FILE_EXCEL = "fileExcel.xls";

  public void write(String nomeSheet, List<ConteggioAccessi> valori) {
    Workbook workbook = new HSSFWorkbook();
    creaSheetConteggioAccessi(valori, workbook);

    try (FileOutputStream outputStream = new FileOutputStream(NOME_FILE_EXCEL)) {
      workbook.write(outputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void creaSheetConteggioAccessi(List<ConteggioAccessi> valori, Workbook workbook) {
    Sheet sheet = workbook.createSheet(CONTEGGIO_ACCESSI);
    int rowCount = 0;
    Row rowHeader = sheet.createRow(rowCount);

    writeHeaderConteggioAccessi(rowHeader);

    for (ConteggioAccessi temp : valori) {
      Row row = sheet.createRow(++rowCount);
      writeConteggioAccessi(temp, row);
    }
  }

}
