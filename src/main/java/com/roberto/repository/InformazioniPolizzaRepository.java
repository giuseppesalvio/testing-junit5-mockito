package com.roberto.repository;

import com.roberto.entitys.PolizzaProva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InformazioniPolizzaRepository {

    private final JdbcTemplate jdbcTemplate;

    public InformazioniPolizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<PolizzaProva> getListaPolizzeAssociateAIdAnagrafica(int idAnagrafica) {

        String sql = "select * from PolizzaProva where idContraente=? or idAssicurato=? or idBeneficiario=?";
        return jdbcTemplate.query(sql, new Object[]{idAnagrafica, idAnagrafica, idAnagrafica},
                (rs, rowNum) ->
                        new PolizzaProva(
                                rs.getInt("numeroPolizza"),
                                rs.getInt("idContraente"),
                                rs.getInt("idAssicurato"),
                                rs.getInt("idBeneficiario")
                        )
        );

    }
}
