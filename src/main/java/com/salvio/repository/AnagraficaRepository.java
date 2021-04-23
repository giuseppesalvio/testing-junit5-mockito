package com.salvio.repository;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnagraficaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Anagrafica> getAllAnagrafiche() {
        return jdbcTemplate.query(
                "select * from anagrafica ",
                (rs, rowNum) ->
                        new Anagrafica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("cognome"),
                                rs.getString("codiceFiscale")));
    }

    public int insert(Anagrafica anagrafica) {
        return jdbcTemplate.update(
                "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
                anagrafica.getId(),
                anagrafica.getNome(),
                anagrafica.getCognome(),
                anagrafica.getCodiceFiscale());
    }

    public Anagrafica ottieniAnagraficaAttraversoCodiceFiscale(String codiceFiscale) {
        return (Anagrafica) jdbcTemplate.queryForObject("select * from anagrafica where codiceFiscale = ? ",
                new Object[]{codiceFiscale}, new BeanPropertyRowMapper(Anagrafica.class));
    }


    public Anagrafica ottieniAnagraficaAttraversoIdAnagrafica(Integer idAnagrafica){

        return (Anagrafica) jdbcTemplate.queryForObject("select * from anagrafica where id=?",
            new Object[]{idAnagrafica},new BeanPropertyRowMapper(Anagrafica.class));
    }

    public List<Anagrafica> getListaAnagraficheAssociateAPolizza(Polizza polizzaAssociata){

       Anagrafica anagraficaContraente= (Anagrafica) jdbcTemplate.queryForObject("select * from anagrafica where id=?",
            new Object[]{polizzaAssociata.getIdContraente()}, new BeanPropertyRowMapper(Anagrafica.class));

        Anagrafica anagraficaAssicurato= (Anagrafica) jdbcTemplate.queryForObject("select * from anagrafica where id=?",
            new Object[]{polizzaAssociata.getIdAssicurato()}, new BeanPropertyRowMapper(Anagrafica.class));

        Anagrafica anagraficaBeneficiario= (Anagrafica) jdbcTemplate.queryForObject("select * from anagrafica where id=?",
            new Object[]{polizzaAssociata.getIdBeneficiario()}, new BeanPropertyRowMapper(Anagrafica.class));


        List<Anagrafica> listaAnagrafiche= new ArrayList<>();

        listaAnagrafiche.add(anagraficaContraente);
        listaAnagrafiche.add(anagraficaAssicurato);
        listaAnagrafiche.add(anagraficaBeneficiario);

        return listaAnagrafiche;

    }

    public List<Anagrafica> getAllAnagrafiche(int id) {
        return null;
    }
}
