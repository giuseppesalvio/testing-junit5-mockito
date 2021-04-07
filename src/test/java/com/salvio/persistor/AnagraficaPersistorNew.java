package com.salvio.persistor;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnagraficaPersistorNew {

  public static void inserisciAnagrafica(JdbcTemplate jdbcTemplate, int id, String nome, String cognome, String codiceFiscale) {
    jdbcTemplate.update(
        "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
        id,
        nome,
        cognome,
        codiceFiscale);

  }

  public static List<Anagrafica> getAnagraficheCorrelateAListaPolizzeFornita(Polizza polizzaFornita){

    List<Anagrafica> listAnagraficheFigureInListaPolizzaFornita= new ArrayList<>();

    listAnagraficheFigureInListaPolizzaFornita.add(new Anagrafica(polizzaFornita.getIdContraente(),"mario","rossi","1234567890123456"));
    listAnagraficheFigureInListaPolizzaFornita.add(new Anagrafica(polizzaFornita.getIdAssicurato(),"mario","rossi","1234567890123456"));
    listAnagraficheFigureInListaPolizzaFornita.add(new Anagrafica(polizzaFornita.getIdBeneficiario(),"mario","rossi","1234567890123456"));





    return listAnagraficheFigureInListaPolizzaFornita;
  }
}
