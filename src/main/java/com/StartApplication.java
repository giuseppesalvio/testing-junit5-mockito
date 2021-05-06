package com;

import com.arca.repository.StatisticheAccessoRepository;
import com.entity.StatisticheAccesso;
import com.service.ExcelStatisticheAccesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class StartApplication {

    @Autowired
    public StatisticheAccessoRepository statisticheAccessoRepository;

    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(StartApplication.class, args);
        ExcelStatisticheAccesso excelStatisticheAccesso=applicationContext.getBean(ExcelStatisticheAccesso.class);
        excelStatisticheAccesso.execute();

    }



}
