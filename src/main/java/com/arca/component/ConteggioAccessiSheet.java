package com.arca.component;

import com.arca.entity.ConteggioAccessi;
import com.arca.repository.StatisticheAccessoRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arca.entity.ConteggioAccessi.writeConteggioAccessi;
import static com.arca.entity.ConteggioAccessi.writeHeaderConteggioAccessi;

@Component
public class ConteggioAccessiSheet {

    final StatisticheAccessoRepository statisticheAccessoRepository;

    public ConteggioAccessiSheet(StatisticheAccessoRepository statisticheAccessoRepository) {
        this.statisticheAccessoRepository = statisticheAccessoRepository;
    }

    public static final String CONTEGGIO_ACCESSI = "ConteggioAccessi";

    public void creaSheet(Workbook workbook) {
        List<ConteggioAccessi> valori = statisticheAccessoRepository.estraiConteggioAccessiUnivoci();
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
