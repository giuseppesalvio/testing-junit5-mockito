package com.arca.component;

import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.StatisticheAccesso;
import com.arca.repository.StatisticheAccessoRepository;
import com.arca.repository.TipologiaDocumentiScaricatiRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DocumentoScaricatoGiornalmenteSheet {

    final TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository;

    public DocumentoScaricatoGiornalmenteSheet(TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository) {
        this.tipologiaDocumentiScaricatiRepository = tipologiaDocumentiScaricatiRepository;
    }

    public static final String STATISTICHE_ACCESSO = "DocumentoScaricatoGiornalmente";

    public void creaSheet(Workbook workbook) {
        List<DocumentoScaricatoGiornalmente> valori = tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiGiornalmente();
        Sheet sheet = workbook.createSheet(STATISTICHE_ACCESSO);
        int rowCount = 0;
        Row rowHeader = sheet.createRow(rowCount);

        DocumentoScaricatoGiornalmente.writeHeader(rowHeader);

        for (DocumentoScaricatoGiornalmente temp : valori) {
            Row row = sheet.createRow(++rowCount);
            DocumentoScaricatoGiornalmente.writeRow(temp, row);
        }
    }
}
