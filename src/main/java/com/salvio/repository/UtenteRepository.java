package com.salvio.repository;

import com.salvio.entitys.PolizzaTest;
import com.salvio.entitys.Utente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtenteRepository {

    private final JdbcTemplate jdbcTemplate;

    public UtenteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Utente estrai(String codiceFiscale) {
        return jdbcTemplate.queryForObject("select * from UTENTE where codiceFiscale=?", new Object[]{codiceFiscale
                },
                new BeanPropertyRowMapper<>(Utente.class));
    }
}
