package com.salvio.simulazioneprogettoarca.repository;

import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import java.util.List;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RichiestaInfoSupermercatoRepository {

  private final JdbcTemplate jdbcTemplate;

  public RichiestaInfoSupermercatoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<SupermercatoDB> estraiListaFiltrataPer(String provinciaRichiesta) {
   return jdbcTemplate.query("select * from supermercatoDB where provincia=?",new Object[]{provinciaRichiesta},
        (rs, rowNum) ->
            new SupermercatoDB(
                rs.getInt("idSupermercato"),
                rs.getString("nomeSupermercato"),
                rs.getString("via"),
                rs.getString("citta"),
                rs.getString("provincia")
            ));
  }
}
