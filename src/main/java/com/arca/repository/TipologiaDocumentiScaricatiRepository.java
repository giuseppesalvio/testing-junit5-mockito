package com.arca.repository;

import com.arca.entity.DocumentoScaricatoGiornalmente;
import com.arca.entity.DocumentoScaricatoTotalmente;
import com.arca.entity.StatisticheAccesso;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TipologiaDocumentiScaricatiRepository {

  private final JdbcTemplate jdbcTemplate;

  public TipologiaDocumentiScaricatiRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public List<DocumentoScaricatoGiornalmente> estraiDocumentiScaricatiGiornalmente() {

    String sql="select TO_char(TMST_DOWNLOAD, 'yyyy/MM/dd') as DATA, AZIONE as TIPO_DOCUMENTO, count(*) as NUMERO_DOWNLOAD\n"
        + "from STATISTICHE\n"
        + "where TO_char(TMST_DOWNLOAD, 'yyyy/MM') >= '2020/10' and AZIONE like 'DOWNLOAD%'\n"
        + "group by AZIONE, TO_char(TMST_DOWNLOAD, 'yyyy/MM/dd')\n"
        + "order by TO_char(TMST_DOWNLOAD, 'yyyy/MM/dd') desc";
    return jdbcTemplate.query(sql,

        (rs, rowNum) ->
            new DocumentoScaricatoGiornalmente(
                rs.getString("DATA"),
                rs.getString("TIPO_DOCUMENTO"),
                rs.getInt("NUMERO_DOWNLOAD")
            )

        );
  }

  public List<DocumentoScaricatoTotalmente> estraiDocumentiScaricatiTotalmente() {

    String sql="select AZIONE as TIPO_DOCUMENTO, count(*) as NUMERO_DOWNLOAD\n"
        + "from (\n"
        + "    select CF, AZIONE, count(*)\n"
        + "    from STATISTICHE\n"
        + "    where AZIONE like 'DOWNLOAD%'\n"
        + "    group by AZIONE, CF\n"
        + ")\n"
        + "group by AZIONE";
    return jdbcTemplate.query(sql,

        (rs, rowNum) ->
            new DocumentoScaricatoTotalmente(
                rs.getString("TIPO_DOCUMENTO"),
                rs.getInt("NUMERO_DOWNLOAD")
            )

    );
  }
}
