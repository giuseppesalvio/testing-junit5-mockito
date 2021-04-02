package com.salvio.repository;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PolizzaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

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
