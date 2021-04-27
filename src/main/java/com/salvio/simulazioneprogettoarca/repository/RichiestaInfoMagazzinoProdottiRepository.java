package com.salvio.simulazioneprogettoarca.repository;

import com.salvio.simulazioneprogettoarca.db.MagazzinoProdottiSupermercatoDB;
import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RichiestaInfoMagazzinoProdottiRepository {

  private final JdbcTemplate jdbcTemplate;

  public RichiestaInfoMagazzinoProdottiRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Integer> estraiIdSupermercatiDisponibili(String nomeProdottoRichiesto, Integer numerositaRichiesta) {

     return jdbcTemplate.query("select idSupermercato from magazzinoProdottiDB where nomeProdotto=" + "'" + nomeProdottoRichiesto + "'"+ " and disponibilita>="
        + numerositaRichiesta,
        (rs, rowNum) ->
            rs.getInt("idSupermercato")
            );

  }
}
