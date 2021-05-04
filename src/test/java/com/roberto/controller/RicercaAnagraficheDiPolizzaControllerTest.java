package com.roberto.controller;

import com.roberto.entitys.DettaglioPolizza;
import com.roberto.services.RicercaAnagraficheDiPolizzaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.roberto.persistor.DettaglioPolizzaPersistor.getListaDettaglioPolizze;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
public class RicercaAnagraficheDiPolizzaControllerTest {

    @InjectMocks
    private RicercaAnagraficheDiPolizzaController ricercaAnagraficheDiPolizzaController;

    @Mock
    private RicercaAnagraficheDiPolizzaService ricercaAnagrafichePerPolizzaService;

    @Test
    void HappyPath() {

        String codiceFiscale = "1234567890123456";

        List<DettaglioPolizza> listaPolizze = getListaDettaglioPolizze(codiceFiscale);
        when(ricercaAnagrafichePerPolizzaService.execute(codiceFiscale)).thenReturn(listaPolizze);

        List<DettaglioPolizza> dettaglioPolizzaList = ricercaAnagraficheDiPolizzaController.execute(codiceFiscale);

        assertThat(dettaglioPolizzaList.get(0).getPolizza().getId()).isEqualTo(1);
        assertThat(dettaglioPolizzaList.get(0).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(0).getBeneficiario().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(0).getContraente().getCodiceFiscale()).isEqualTo(codiceFiscale);

        assertThat(dettaglioPolizzaList.get(1).getPolizza().getId()).isEqualTo(2);
        assertThat(dettaglioPolizzaList.get(1).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(1).getBeneficiario().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(1).getContraente().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
    }

}
