package com.arca.repository;

import com.entity.Book;
import com.entity.StatisticheAccesso;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticheAccessoRepository {

    final JdbcTemplate jdbcTemplate;

    public StatisticheAccessoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StatisticheAccesso> retrive(){
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


}
