package com.arca.repository;

import com.StartApplication;
import com.arca.entity.ConteggioAccessi;
import com.arca.entity.StatisticheAccesso;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
        //test che va in errore per l'inserimento di una particolare combinazione di valori es(stesso cf che entra stessa banca stesso giorno)
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

        List<StatisticheAccesso> actual =statisticheAccessoRepository.retriveAccessiSSO();
        StatisticheAccesso expected=new StatisticheAccesso("29/04/2021","Sondrio",1);
        Assertions.assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);
        Assertions.assertThat(actual.size()).isEqualTo(2);

    }

    @Test
    public void conteggioAccessiGGRepositoryOK(){
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");
        jdbcTemplate.update("insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('28-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop')");

        List<ConteggioAccessi> listaRestituita =statisticheAccessoRepository.estraiConteggioAccessiUnivoci();
        ConteggioAccessi expected= new ConteggioAccessi("29/04/2021",1,0);
        Assertions.assertThat(listaRestituita.get(0)).isEqualToComparingFieldByField(expected);
        Assertions.assertThat(listaRestituita.size()).isEqualTo(2);
    }







}
