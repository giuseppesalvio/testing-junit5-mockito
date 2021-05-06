package com.arca.repository;

import com.StartApplication;
import com.entity.StatisticheAccesso;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StartApplication.class})
public class StatisticheAccessoRepositoryTest
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StatisticheAccessoRepository statisticheAccessoRepository;

    @Test
    public void statisticheAccessRepository()
    {
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

        List<StatisticheAccesso> actual =statisticheAccessoRepository.retrive();
        StatisticheAccesso expected=new StatisticheAccesso("29/04/2021","Sondrio",1);
        Assertions.assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);
        Assertions.assertThat(actual.size()).isEqualTo(2);

    }
    @Test
    public void statisticheOrarioAccessoPersonaTest()
    {
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 17:59:30,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

        List<StatisticheAccesso> actual =statisticheAccessoRepository.retrive();
        StatisticheAccesso expected=new StatisticheAccesso("29/04/2021","Sondrio",1);
        Assertions.assertThat(actual.get(0).getConteggioAccessi()).isEqualToComparingFieldByField(expected.getConteggioAccessi());
        Assertions.assertThat(actual.size()).isEqualTo(1);

    }





}
