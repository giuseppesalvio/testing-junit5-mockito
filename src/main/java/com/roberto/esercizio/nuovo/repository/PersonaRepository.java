package com.roberto.esercizio.nuovo.repository;

import com.roberto.esercizio.nuovo.db.PersonaDB;
import com.roberto.esercizio.nuovo.dto.NomeCognomeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PersonaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PersonaDB getPersonaBy(NomeCognomeDto nomeCognomeDto) {

        return jdbcTemplate.queryForObject(
                "select * from PERSONA where nome= '" + nomeCognomeDto.getNome() + "' and cognome = '" + nomeCognomeDto.getCognome() + "'",
                (rs, rowNum) ->
                        new PersonaDB(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("cognome"),
                                rs.getString("codiceFiscale"),
                                rs.getInt("indirizzo")));

    }
}
