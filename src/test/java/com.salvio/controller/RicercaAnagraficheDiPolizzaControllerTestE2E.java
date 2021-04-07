package com.salvio.controller;

import static com.salvio.persistor.AnagraficaPersistorNew.inserisciAnagrafica;
import static com.salvio.persistor.PolizzaPersistor.inserisciPolizza;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.salvio.StartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@SpringBootTest(classes = StartApplication.class)
@AutoConfigureMockMvc
public class RicercaAnagraficheDiPolizzaControllerTestE2E {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    void HappyPath() throws Exception {

        String codiceFiscale = "1234567890123456";



        inserisciPolizza(jdbcTemplate,1, 9999, 9999, 9999);
        inserisciPolizza(jdbcTemplate,2, 9999, 8888, 2222);
        inserisciPolizza(jdbcTemplate,3, 8888, 8888, 2222);
        inserisciPolizza(jdbcTemplate,4, 2222, 2222, 2222);


//mancano le insert delle anagrafe
            inserisciAnagrafica(jdbcTemplate,9999,"Mario","Rossi","1234567890123456");
            inserisciAnagrafica(jdbcTemplate,8888,"Gennaro","Esposito","gnnsps1234567890");
            inserisciAnagrafica(jdbcTemplate,2222,"Pippo","Pluto","pppplt1234567890");


        //ResultActions resultActions = this.mockMvc.perform(post("/ricerca-anagrafiche-di-polizza",codiceFiscale)).andDo(print()).andExpect(status().isOk());
       // String result= resultActions.andReturn().getResponse().getContentAsString();


        //questo resault è il json in formato testo, per fare l'assert si fa sul testo enorme
        //altrimenti, cerchi su internet come trasformare testo di un json in oggetto.

//        assertThat(dettaglioPolizzaList.get(0).getPolizza().getId()).isEqualTo(1);
//        assertThat(dettaglioPolizzaList.get(0).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
//        assertThat(dettaglioPolizzaList.get(0).getBeneficiario().getCodiceFiscale()).isEqualTo(codiceFiscale);
//        assertThat(dettaglioPolizzaList.get(0).getContraente().getCodiceFiscale()).isEqualTo(codiceFiscale);
//
//        assertThat(dettaglioPolizzaList.get(1).getPolizza().getId()).isEqualTo(2);
//        assertThat(dettaglioPolizzaList.get(1).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
//        assertThat(dettaglioPolizzaList.get(1).getBeneficiario().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
//        assertThat(dettaglioPolizzaList.get(1).getContraente().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
    }

}
