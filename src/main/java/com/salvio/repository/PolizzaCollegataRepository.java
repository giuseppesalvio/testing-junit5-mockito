package com.salvio.repository;

import com.salvio.entitys.PolizzaCollegata;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PolizzaCollegataRepository {

    private JdbcTemplate jdbcTemplate;

    public PolizzaCollegataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<PolizzaCollegata> getPolizzaCollegataDaRepo(String cfInput) {


        List<PolizzaCollegata> listaPolizze= jdbcTemplate.query(
                "select * from polizzaCollegata where( idContraente = (select id from anagrafica where codiceFiscale=?) or idAssicurato = (select id from anagrafica where codiceFiscale=?) or idBeneficiario= (select id from anagrafica where codiceFiscale=?)  )", new Object[]{cfInput,cfInput,cfInput
                },
                new BeanPropertyRowMapper(PolizzaCollegata.class));
        return listaPolizze;
    }
}
