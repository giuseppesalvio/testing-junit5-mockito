package com.roberto.persistor;

import com.roberto.entitys.PolizzaUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PolizzaUtentePersistor {

    public static void inserisciPolizzaUtente(PolizzaUtente polizzaUtente,JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update(
                "insert into POLIZZA_UTENTE(id ,numeroPolizza ,utente_id ) values " +
                        "(" + polizzaUtente.getId() + ",'" + polizzaUtente.getNumeroPolizza() + "'," + polizzaUtente.getUtenteid() + ")");
    }
}
