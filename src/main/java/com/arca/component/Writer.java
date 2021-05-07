package com.arca.component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class Writer {
  final ConteggioAccessiSheet conteggioAccessiSheet;
  final StatisticheAccessoSheet statisticheAccessoSheet;
  final DocumentoScaricatoGiornalmenteSheet documentoScaricatoGiornalmenteSheet;
  final DocumentoScaricatoTotalmenteSheet documentoScaricatoTotalmenteSheet;

  public Writer(ConteggioAccessiSheet conteggioAccessiSheet, StatisticheAccessoSheet statisticheAccessoSheet, DocumentoScaricatoGiornalmenteSheet documentoScaricatoGiornalmenteSheet, DocumentoScaricatoTotalmenteSheet documentoScaricatoTotalmenteSheet) {
    this.conteggioAccessiSheet = conteggioAccessiSheet;
    this.statisticheAccessoSheet = statisticheAccessoSheet;
    this.documentoScaricatoGiornalmenteSheet = documentoScaricatoGiornalmenteSheet;
    this.documentoScaricatoTotalmenteSheet = documentoScaricatoTotalmenteSheet;
  }

  public static final String NOME_FILE_EXCEL = "fileExcel.xls";


  public void write() {
    Workbook workbook = new HSSFWorkbook();

    conteggioAccessiSheet.creaSheet(workbook);
    statisticheAccessoSheet.creaSheet(workbook);
    documentoScaricatoGiornalmenteSheet.creaSheet(workbook);
    documentoScaricatoTotalmenteSheet.creaSheet(workbook);

    try (FileOutputStream outputStream = new FileOutputStream(NOME_FILE_EXCEL)) {
      workbook.write(outputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



}
