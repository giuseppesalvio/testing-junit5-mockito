package com.arca.repository;

import com.arca.entity.ConteggioAccessi;
import com.arca.entity.StatisticheAccesso;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticheAccessoRepository {

    final JdbcTemplate jdbcTemplate;

    public StatisticheAccessoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StatisticheAccesso> retriveAccessiSSO(){
        String sql = "select TO_char(TMST_ACCESSO, 'dd/MM/yyyy') as DATA, BANCA_SSO, count(*) as conteggioAccessi \n"
            + " from STATISTICHE_ACCESSO\n"
            + " where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10' and FLG_ACCESSO_SSO = 'S'\n"
            + " group by BANCA_SSO, TO_char(TMST_ACCESSO, 'dd/MM/yyyy')\n"
            + " order by TO_date(TO_char(TMST_ACCESSO, 'dd/MM/yyyy'), 'dd/MM/yyyy') desc";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new StatisticheAccesso(
                                rs.getString("DATA"),
                                rs.getString("BANCA_SSO"),
                                rs.getInt("conteggioAccessi")
                        )
        );
    }


    public List<ConteggioAccessi> estraiConteggioAccessiUnivoci() {
        String sql =" select T_ACCESSIUNICI.GIORNOCONSIDERATO,\n"
            + "    T_ACCESSIUNICI.ACCESSIUNICIGG,\n"
            + "    (T_ACCESSITOTALI.CONTEGGIOACCESSITOTALIGG - T_ACCESSIUNICI.ACCESSIUNICIGG) as ACCESSINONUNICIGG\n"
            + "from (\n"
            + "    select GIORNOCONSIDERATO, count(*) as ACCESSIUNICIGG\n"
            + "    from (\n"
            + "        select TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy') as GIORNOCONSIDERATO\n"
            + "        from STATISTICHE_ACCESSO\n"
            + "        where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10'\n"
            + "        group by CF, TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy')\n"
            + "    )\n"
            + "    group by GIORNOCONSIDERATO\n"
            + "    order by TO_DATE(GIORNOCONSIDERATO, 'dd/MM/yyyy') desc\n"
            + ") T_ACCESSIUNICI\n"
            + "         join\n"
            + "(\n"
            + "    select GIORNOCONSIDERATO, count(*) as CONTEGGIOACCESSITOTALIGG\n"
            + "    from (\n"
            + "        select TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy') as GIORNOCONSIDERATO\n"
            + "        from STATISTICHE_ACCESSO\n"
            + "        where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10'\n"
            + "        group by ID_ACCESSO, TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy')\n"
            + "    )\n"
            + "    group by GIORNOCONSIDERATO\n"
            + "    order by TO_DATE(GIORNOCONSIDERATO, 'dd/MM/yyyy') desc\n"
            + ") T_ACCESSITOTALI on T_ACCESSIUNICI.GIORNOCONSIDERATO = T_ACCESSITOTALI.GIORNOCONSIDERATO\n";

        return jdbcTemplate.query(
                                   sql,
            (rs, rowNum) ->
                new ConteggioAccessi(
                    rs.getString("GIORNOCONSIDERATO"),
                    rs.getInt("ACCESSIUNICIGG"),
                    rs.getInt("ACCESSINONUNICIGG")
                ));
    }
}
