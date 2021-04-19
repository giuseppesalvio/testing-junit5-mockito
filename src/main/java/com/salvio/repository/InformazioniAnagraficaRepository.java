package com.salvio.repository;

import com.salvio.entitys.AnagraficaProva;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformazioniAnagraficaRepository {

    private final JdbcTemplate jdbcTemplate;

    public InformazioniAnagraficaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AnagraficaProva getAnagraficaDaCodiceFiscale(String codiceFiscale) {

        return (AnagraficaProva) jdbcTemplate.queryForObject(
                "Select * from AnagraficaProva where codiceFiscale=?",
                new Object[]{codiceFiscale},
                new BeanPropertyRowMapper(AnagraficaProva.class)
        );
    }

    public AnagraficaProva getAnagraficheDaIdAnagrafica(Integer idAnagrafica) {

        return (AnagraficaProva) jdbcTemplate.queryForObject(
                "Select * from AnagraficaProva where idAnagrafica=?",
                new Object[]{idAnagrafica},
                new BeanPropertyRowMapper(AnagraficaProva.class)
        );
    }
}
