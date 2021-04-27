package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoMagazzinoProdottiRepository;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoSupermercatoRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = StartApplication.class)
public class RichiestaInfoSupermercatoRepositoryIntegrationTest {

  @Autowired
  private RichiestaInfoSupermercatoRepository richiestaInfoSupermercatoRepository;

  @Autowired
  private RichiestaInfoMagazzinoProdottiRepository richiestaInfoMagazzinoProdottiRepository;


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaInfoSupermercatoRepoOk() {

    String provinciaRichiesta = "verona";

    inserisciSupermercato(jdbcTemplate, 1, "coop", "garibaldi", "baldaria", "verona");
    inserisciSupermercato(jdbcTemplate, 2, "lidl", "mazzini", "minerbe", "verona");
    inserisciSupermercato(jdbcTemplate, 3, "eurospin", "cavour", "legnago", "verona");
    inserisciSupermercato(jdbcTemplate, 4, "famila", "napoleone", "lonigo", "vicenza");

    List<SupermercatoDB> lista = richiestaInfoSupermercatoRepository.estraiListaFiltrataPer(provinciaRichiesta);
    Assertions.assertThat(lista.size()).isEqualTo(3);

  }

  @Test
  public void verificaInfoMagazzinoRepoOk() {

    String nomeProdottoRichiesto = "pane";
    Integer numerositaRichiesta = 10;

    inserisciMagazzino(jdbcTemplate, 1, 9999, "pane", 0.30, 100);
    inserisciMagazzino(jdbcTemplate, 1, 9998, "farina", 0.20, 1000);
    inserisciMagazzino(jdbcTemplate, 2, 8888, "pane", 0.40, 50);
    inserisciMagazzino(jdbcTemplate, 3, 5555, "pane", 0.45, 5);

    List<Integer> listaDiIdSuper=richiestaInfoMagazzinoProdottiRepository.estraiIdSupermercatiDisponibili(nomeProdottoRichiesto,numerositaRichiesta);

    Assertions.assertThat(listaDiIdSuper.size()).isEqualTo(2);

  }

  private static void inserisciSupermercato(JdbcTemplate jdbcTemplate, Integer id, String nome, String via, String citta,
      String provincia) {

    jdbcTemplate.update("insert into SupermercatoDB(idSupermercato,nomeSupermercato,via,citta,provincia) values(?,?,?,?,?)",

        id,
        nome,
        via,
        citta,
        provincia
    );


  }

  private static void inserisciMagazzino(JdbcTemplate jdbcTemplate, Integer id, Integer codice, String nome, Double costo,
      Integer disponibilita) {

    jdbcTemplate.update(
        "insert into MagazzinoProdottiDB(idSupermercato,codiceProdotto,nomeProdotto,costoUnitario,disponibilita) values(?,?,?,?,?)",

        id,
        codice,
        nome,
        costo,
        disponibilita

    );


  }

}
