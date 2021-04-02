package com.salvio.services;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.repository.AnagraficaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RicercaAnagraficheDiPolizzaServiceTest {

    @InjectMocks
    private RicercaAnagraficheDiPolizzaService ricercaAnagraficheDiPolizzaService;

    @Mock
    private AnagraficaRepository anagraficaRepository;

    @Test
    void execute() {
        String codiceFiscale = "1234567890123456";

        Anagrafica anagrafica = new Anagrafica(9999, "Mario", "Rossi", "1234567890123456");
        when(anagraficaRepository.getByCodiceFiscale(codiceFiscale)).thenReturn(anagrafica);
        List<DettaglioPolizza> dettaglioPolizzaList = ricercaAnagraficheDiPolizzaService.execute(codiceFiscale);

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
