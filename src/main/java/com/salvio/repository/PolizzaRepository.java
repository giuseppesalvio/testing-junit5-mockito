package com.salvio.repository;

import com.salvio.entitys.Polizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PolizzaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PolizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Polizza> cercaByCodiceAnagrafica(Integer idAnagrafica) {
        return jdbcTemplate.query(
                "select * from polizza where idContraente   = ? OR " +
                        "    idAssicurato  = ? OR " +
                        "    idBeneficiario = ? ",
                new Object[]{idAnagrafica, idAnagrafica, idAnagrafica},
                (rs, rowNum) ->
                        new Polizza(
                                rs.getInt("id"),
                                rs.getInt("idContraente"),
                                rs.getInt("idAssicurato"),
                                rs.getInt("idBeneficiario")
                        )
        );
    }
}
