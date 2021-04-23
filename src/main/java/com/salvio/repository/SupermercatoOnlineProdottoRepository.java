package com.salvio.repository;

import com.salvio.entitys.ProdottoSupermercatoProva;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SupermercatoOnlineProdottoRepository {

  private final JdbcTemplate jdbcTemplate;

  public SupermercatoOnlineProdottoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ProdottoSupermercatoProva> estraiTutti() {
    return jdbcTemplate.query("select * from prodottoSupermercato",
        (rs, rowNum) ->
            new ProdottoSupermercatoProva(
                rs.getString("codiceProdotto"),
                rs.getString("nomeProdotto"),
                rs.getDouble("costoProdotto"),
                rs.getString("provenienzaProdotto"),
                rs.getString("scadenzaProdotto"),
                rs.getString("categoriaProdotto")
            )
    );
  }

  public ProdottoSupermercatoProva estraiTramite(String codiceProdotto) {

    return jdbcTemplate.queryForObject("select * from prodottoSupermercato where codiceProdotto=?",
        new Object[]{codiceProdotto},
        (rs, rowNum) ->
            new ProdottoSupermercatoProva(
                rs.getString("codiceProdotto"),
                rs.getString("nomeProdotto"),
                rs.getDouble("costoProdotto"),
                rs.getString("provenienzaProdotto"),
                rs.getString("scadenzaProdotto"),
                rs.getString("categoriaProdotto")
            )
    );
  }
}
