package com;

import com.arca.repository.StatisticheAccessoRepository;
import com.entity.StatisticheAccesso;
import com.service.ExcelStatisticheAccesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class StartApplication {

    @Autowired
    public StatisticheAccessoRepository statisticheAccessoRepository;

    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        statisticheAccesso();

    }

    private void statisticheAccesso() {
        ExcelStatisticheAccesso excelStatisticheAccesso = new ExcelStatisticheAccesso();

        List<StatisticheAccesso> list = statisticheAccessoRepository.retrive();


        String excelFilePath = "NiceJavaBooks.xls";

        try {
            excelStatisticheAccesso.writeExcel(list, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
