package com;

import com.arca.repository.StatisticheAccessoRepository;
import com.arca.component.ExcelStatisticheAccessoComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartApplication {

    @Autowired
    public StatisticheAccessoRepository statisticheAccessoRepository;

    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(StartApplication.class, args);
        ExcelStatisticheAccessoComponent excelStatisticheAccessoComponent =applicationContext.getBean(
            ExcelStatisticheAccessoComponent.class);
        excelStatisticheAccessoComponent.execute();

    }



}
