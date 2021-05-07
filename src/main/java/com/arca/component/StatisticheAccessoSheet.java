package com.arca.component;

import com.arca.entity.StatisticheAccesso;
import com.arca.repository.StatisticheAccessoRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StatisticheAccessoSheet {

    final StatisticheAccessoRepository statisticheAccessoRepository;

    public StatisticheAccessoSheet(StatisticheAccessoRepository statisticheAccessoRepository) {
        this.statisticheAccessoRepository = statisticheAccessoRepository;
    }

    public static final String STATISTICHE_ACCESSO = "StatisticheAccesso";

    public void creaSheet(Workbook workbook) {
        List<StatisticheAccesso> valori = statisticheAccessoRepository.retriveAccessiSSO();
        Sheet sheet = workbook.createSheet(STATISTICHE_ACCESSO);
        int rowCount = 0;
        Row rowHeader = sheet.createRow(rowCount);

        StatisticheAccesso.writeHeader(rowHeader);

        for (StatisticheAccesso temp : valori) {
            Row row = sheet.createRow(++rowCount);
            StatisticheAccesso.writeRow(temp, row);
        }
    }
}
