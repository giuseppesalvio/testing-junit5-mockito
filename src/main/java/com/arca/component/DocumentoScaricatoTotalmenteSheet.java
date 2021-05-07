package com.arca.component;

import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.DocumentoScaricatoTotalmente;
import com.arca.repository.TipologiaDocumentiScaricatiRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DocumentoScaricatoTotalmenteSheet {

    final TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository;

    public DocumentoScaricatoTotalmenteSheet(TipologiaDocumentiScaricatiRepository tipologiaDocumentiScaricatiRepository) {
        this.tipologiaDocumentiScaricatiRepository = tipologiaDocumentiScaricatiRepository;
    }

    public static final String STATISTICHE_ACCESSO = "DocumentoScaricatoTotalmente";

    public void creaSheet(Workbook workbook) {
        List<DocumentoScaricatoTotalmente> valori = tipologiaDocumentiScaricatiRepository.estraiDocumentiScaricatiTotalmente();
        Sheet sheet = workbook.createSheet(STATISTICHE_ACCESSO);
        int rowCount = 0;
        Row rowHeader = sheet.createRow(rowCount);

        DocumentoScaricatoTotalmente.writeHeader(rowHeader);

        for (DocumentoScaricatoTotalmente temp : valori) {
            Row row = sheet.createRow(++rowCount);
            DocumentoScaricatoTotalmente.writeRow(temp, row);
        }
    }
}
