package com.salvio.persistor;

import com.salvio.entitys.ProdottoInfoBaseProva;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProdottiSupermercatoPersistor {


  public static void inserisciProdottoSupermercato(JdbcTemplate jdbcTemplate, String codiceProdotto, String nomeProdotto,
      Double costoProdotto, String provenienzaProdotto, String scadenzaProdotto, String categoriaProdotto)

          {
            jdbcTemplate.update(
                "insert into prodottoSupermercato(codiceProdotto,nomeProdotto,costoProdotto,"
                    + "provenienzaProdotto,scadenzaProdotto,categoriaProdotto) values (?,?,?,?,?,?)",
                codiceProdotto,
                nomeProdotto,
                costoProdotto,
                provenienzaProdotto,
                scadenzaProdotto,
                categoriaProdotto
            );
          }



}
