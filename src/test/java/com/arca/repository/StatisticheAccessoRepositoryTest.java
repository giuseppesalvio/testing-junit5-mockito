package com.arca.repository;

import com.StartApplication;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StartApplication.class})
public class StatisticheAccessoRepositoryTest
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void statisticheAccessRepository()
    {
        jdbcTemplate.update("insert into POLIZZA_UTENTE (id,numeroPolizza,utente_id) values (9,'tstnumpol',25)");


    }





}
