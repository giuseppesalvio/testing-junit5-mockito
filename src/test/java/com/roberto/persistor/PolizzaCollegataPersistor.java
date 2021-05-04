package com.roberto.persistor;

import org.springframework.jdbc.core.JdbcTemplate;

public class PolizzaCollegataPersistor {

    public static void inserisciPolizza(JdbcTemplate jdbcTemplate, int numeroPolizza, int idContraente, int idAssicurato, int idBeneficiario ){

        jdbcTemplate.update("Insert into polizza(numeroPoliza,idContraente,idAssicurato,idBeneficiario) values(?,?,?,?)",
                numeroPolizza,
                idContraente,
                idAssicurato,
                idBeneficiario);
    }

}
