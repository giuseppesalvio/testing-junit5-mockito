package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.db.DipendenteDB;
import com.salvio.simulazioneprogettoarca.repository.AziendaRepository;
import com.salvio.simulazioneprogettoarca.repository.DipendentiRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class AziendaDipendentiRepositoryIntegrationTest {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private AziendaRepository aziendaRepository;

  @Autowired
  private DipendentiRepository dipendentiRepository;

  @Test
  public void verificaAziendaRepoTestOk() {

    String nomeAzienda = "fincons";
    inserisciAziendaADB(jdbcTemplate, 10, "mrtmcl", "fincons", "ITALIA", LocalDate.of(1983, 01, 01));

    List<Integer> listaIdAziende = aziendaRepository.estraiAttraverso(nomeAzienda);

    Assertions.assertThat(listaIdAziende.size()).isEqualTo(1);


  }

  public static void inserisciAziendaADB(JdbcTemplate jdbcTemplate, Integer pIvaAzienda, String codiceFiscaleProprietario,
      String nomeAzienda, String nazione,
      LocalDate dataFondazione) {

    jdbcTemplate.update(
        "insert into AziendaDB(pIvaAzienda,codiceFiscaleProprietario,nomeAzienda,nazione,dataFondazione) values(?,?,?,?,?)",

        pIvaAzienda, codiceFiscaleProprietario, nomeAzienda, nazione, dataFondazione
    );

  }

  @Test
  public void verificaDipendentiRepoTestOK() {

    inserisciDipendenteDB(jdbcTemplate, 10, "pppplt", "pippo", "pluto", "manager", 2000.00, LocalDate.of(2017, 01, 01));
    inserisciDipendenteDB(jdbcTemplate, 10, "mrossi", "mario", "rossi", "developer", 1000.00, LocalDate.of(2020, 01, 01));
    List<Integer> listaCodiciAzienda = new ArrayList<>();
    listaCodiciAzienda.add(new Integer(10));
    List<DipendenteDB> listaDB = dipendentiRepository.estraiListaDipendentiAttraverso(listaCodiciAzienda);
    Assertions.assertThat(listaDB.size()).isEqualTo(2);
  }

  public static void inserisciDipendenteDB(JdbcTemplate jdbcTemplate, Integer pIvaAzienda, String cf, String nome, String cognome,
      String ruolo,
      Double stipendio, LocalDate dataAssunzione) {

    jdbcTemplate.update("insert into dipendenteDb(pIvaAzienda,codiceFiscale,nome,cognome,ruolo,stipendio,dataAssunzione) values (?,?,?,?,?,?,?)",

        pIvaAzienda, cf, nome, cognome, ruolo, stipendio, dataAssunzione

    );

  }


}
