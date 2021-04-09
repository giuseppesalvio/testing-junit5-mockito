package com.salvio.repository;

import com.salvio.StartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest(classes = StartApplication.class)
public class PolizzaCollegataRepositoryIntegrationTest {

    @Autowired
    private PolizzaCollegataRepository polizzaCollegataRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void estraiPolizzaCollegataAttraversoCf(){
        jdbcTemplate.update("insert into anagrafica(id,nome,cognome,codiceFiscale) values (1,'mario','rossi','mro'),(2,'ciccio','pasticcio','zzz'),(3,'pippo','baudo','pba'),(4,'gennaro','esposito','ges');");
        jdbcTemplate.update("insert into polizzaCollegata(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values (1,2,2,2),(2,4,2,3),(3,4,3,3);");

        List<PolizzaCollegata> listaPolizzeCollegate = polizzaCollegataRepository.getPolizzaCollegataDaRepo("zzz");

        Assertions.assertEquals(new Integer(1),listaPolizzeCollegate.get(0).getNumeroPolizza());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.get(0).getIdContraente());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.get(0).getIdAssicurato());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.get(0).getIdBeneficiario());


        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.get(1).getNumeroPolizza());
        Assertions.assertEquals(new Integer(4),listaPolizzeCollegate.get(1).getIdContraente());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.get(1).getIdAssicurato());
        Assertions.assertEquals(new Integer(3),listaPolizzeCollegate.get(1).getIdBeneficiario());

        /*
        Assertions.assertEquals(new Integer(1),listaPolizzeCollegate.getNumeroPolizza());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.getIdContraente());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.getIdAssicurato());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegate.getIdAssicurato());
        */

    }

}
