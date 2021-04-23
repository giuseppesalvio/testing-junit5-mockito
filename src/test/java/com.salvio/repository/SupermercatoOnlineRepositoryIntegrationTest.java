package com.salvio.repository;

import static com.salvio.persistor.ProdottiSupermercatoPersistor.inserisciProdottoSupermercato;

import com.salvio.StartApplication;
import com.salvio.entitys.InfoMagazzinoSupermercatoProva;
import com.salvio.entitys.ProdottoSupermercatoProva;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@SpringBootTest(classes= StartApplication.class)
public class SupermercatoOnlineRepositoryIntegrationTest {

  @Autowired
  private SupermercatoOnlineProdottoRepository supermercatoOnlineProdottoRepository;
  @Autowired
  private SupermercatoOnlineMagazzinoRepository supermercatoOnlineMagazzinoRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void funzionamentoProdottoRepoOk(){
    String codiceProdotto="P0001";
    inserisciProdottoSupermercato(jdbcTemplate, "P0001", "farina", 0.35, "ITALIA","2021-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0002", "latte", 0.60, "ITALIA","2021-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0003", "pasta", 0.55, "ITALIA","2022-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0004", "acqua", 0.25, "ITALIA","2025-12-31","alimentare");
    inserisciProdottoSupermercato(jdbcTemplate, "P0005", "pentola", 5.00, "GERMANIA","null","cucina");

    List<ProdottoSupermercatoProva> listaAll=supermercatoOnlineProdottoRepository.estraiTutti();

    ProdottoSupermercatoProva prodottoRestituito= supermercatoOnlineProdottoRepository.estraiTramite(codiceProdotto);

    Assertions.assertThat(listaAll.size()).isEqualTo(5);
    Assertions.assertThat(prodottoRestituito.getNomeProdotto()).isEqualTo("farina");

  }

  @Test
  public void funzionamentoMagazzinoRepoOk(){
    String codiceProdotto="P0001";
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0001",100);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0002",200);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0003",400);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0004",1000);
    inserisciDisponibilitaProdotto(jdbcTemplate,"P0005",50);

    InfoMagazzinoSupermercatoProva infoDispobilitaProdotto =supermercatoOnlineMagazzinoRepository.estraiDisponibilita(codiceProdotto);

    Assertions.assertThat(infoDispobilitaProdotto.getDisponibilita()).isEqualTo(100);
  }




  public void inserisciDisponibilitaProdotto(JdbcTemplate jdbcTemplate,String codice,Integer disponibilita){

    jdbcTemplate.update(
        "insert into InfoMagazzinoSupermercato(codiceProdotto,disponibilita) values(?,?) ",

        codice,
        disponibilita
    );

  }

}
