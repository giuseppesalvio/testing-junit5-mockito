package com.salvio.controller;

import com.salvio.StartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class E2EControllerPolizzaCollegataTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void controlloPolizzaOk() throws Exception{

     jdbcTemplate.update("insert into polizzaCollegata(numeroPolizza,idContraente,idAssicurato,idBeneficiario) values (1,2,2,2),(2,4,2,3),(3,3,4,4)");
     jdbcTemplate.update("insert into anagrafica(id,nome,cognome,codiceFiscale) values (1,'mario','rossi','mro'),(2,'ciccio','pasticcio','zzz'),(3,'pippo','baudo','pba'),(4,'gennaro','esposito','ges')");


     String cf= "zzz";

     ResultActions resultActions = this.mockMvc.perform(get("/getListaPolizzeAssociateAlCfInserito").param("cfInput",cf)).andDo(print()).andExpect(status().isOk());

     String expected= "[{\"numeroPolizza\":1,\"idContraente\":2,\"idAssicurato\":2,\"idBeneficiario\":2},{\"numeroPolizza\":2,\"idContraente\":4,\"idAssicurato\":2,\"idBeneficiario\":3}]"; ;
     String result=resultActions.andReturn().getResponse().getContentAsString();

     Assertions.assertEquals(expected,result);



    }


}
