package com.salvio.services;

import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.Polizza;
import com.salvio.repository.AnagraficaRepository;
import com.salvio.repository.PolizzaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.salvio.persistor.AnagraficaPersistorNew.getAnagraficheCorrelateAListaPolizzeFornita;
import static com.salvio.persistor.DettaglioPolizzaPersistor.getListaDettaglioPolizze;
import static com.salvio.persistor.DettaglioPolizzaPersistor.getListaPolizze;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class RicercaAnagraficheDiPolizzaServiceTest {

    @InjectMocks
    private RicercaAnagraficheDiPolizzaService ricercaAnagraficheDiPolizzaService;

    @Mock
    private AnagraficaRepository anagraficaRepository;

    @Mock
    private PolizzaRepository polizzaRepository;


    @Test
    void executeServiceTest() {
        String codiceFiscale = "1234567890123456";

        Anagrafica anagraficaDelCodiceFiscale = new Anagrafica(9999, "Mario", "Rossi", "1234567890123456");
        List<Polizza> listaPolizze = getListaPolizze();             // [{1, 9999, 9999, 9999},{ 2, 9999, 8888, 2222}]
        List<Anagrafica> listaAnagrafiche =getAnagraficheCorrelateAListaPolizzeFornita(listaPolizze.get(0)); //[{9999,"mario","rossi","12345.."},{},{}]
        when(anagraficaRepository.ottieniAnagraficaAttraversoCodiceFiscale(codiceFiscale)).thenReturn(anagraficaDelCodiceFiscale);
        when(polizzaRepository.cercaByCodiceAnagrafica(anagraficaDelCodiceFiscale.getId())).thenReturn(listaPolizze);
        when(anagraficaRepository.getListaAnagraficheAssociateAPolizza(listaPolizze.get(0))).thenReturn(listaAnagrafiche);


        List<DettaglioPolizza> dettaglioPolizzaList = ricercaAnagraficheDiPolizzaService.execute(codiceFiscale);

        assertThat(dettaglioPolizzaList.get(0).getPolizza().getId()).isEqualTo(1);
        assertThat(dettaglioPolizzaList.get(0).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(0).getBeneficiario().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(0).getContraente().getCodiceFiscale()).isEqualTo(codiceFiscale);



        /*
        assertThat(dettaglioPolizzaList.get(1).getPolizza().getId()).isEqualTo(2);
        assertThat(dettaglioPolizzaList.get(1).getAssicurato().getCodiceFiscale()).isEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(1).getBeneficiario().getCodiceFiscale()).isNotEqualTo(codiceFiscale);
        assertThat(dettaglioPolizzaList.get(1).getContraente().getCodiceFiscale()).isNotEqualTo(codiceFiscale);

         */
    }


}
