package com.roberto.repository;

import com.roberto.entitys.PolizzaUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryPolizzeUtente
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public PolizzaUtente getPolizzeutenteByFscalCode(String codiceFiscaleTest)
    {
        return jdbcTemplate.queryForObject(
                "select * from polizza  p join utente u on p.utente_id=u.id where u.codicefiscale=?", new Object[]{codiceFiscaleTest},
                (rs, rowNum) ->
                        new PolizzaUtente(
                                rs.getLong("id"),
                                rs.getString(
                                        "numeropolizza"),
                                rs.getLong(
                                        "utenteid")
                        )
        );
    }
}
