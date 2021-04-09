package com.salvio.services;

import static org.mockito.Mockito.when;

import com.salvio.entitys.AnagraficaProva;
import com.salvio.entitys.DettaglioPolizza;
import com.salvio.entitys.DettaglioPolizzaProva;
import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.InformazioniAnagraficaRepository;
import com.salvio.repository.InformazioniPolizzaRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InformazioniDettaglioPolizzaServiceTest {

  @InjectMocks
  private InformazioniPolizzaService informazioniPolizzaService;

  @Mock
  private InformazioniPolizzaRepository informazioniPolizzaRepository;
  @Mock
  private InformazioniAnagraficaRepository informazioniAnagraficaRepository;

  @Test
  public void controlloExecuteService(){
    String codiceFiscale="123456";
    AnagraficaProva anagraficaProva= AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale(codiceFiscale).build();
    List<PolizzaProva> listaPolizzeDefault= new ArrayList<>();
    listaPolizzeDefault.add(new PolizzaProva(1,9999,9999,9999));
    listaPolizzeDefault.add(new PolizzaProva(2,8888,9999,2222));

    List<AnagraficaProva> listaAnagraficheDefault= new ArrayList<>();
    listaAnagraficheDefault.add(AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build());
    listaAnagraficheDefault.add(AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build());
    listaAnagraficheDefault.add(AnagraficaProva.builder().idAnagrafica(9999).nome("mario").cognome("rossi").codiceFiscale("123456").build());

    when(informazioniAnagraficaRepository.getAnagraficaDaCodiceFiscale(codiceFiscale)).thenReturn(anagraficaProva);
    when(informazioniPolizzaRepository.getListaPolizzeAssociateAIdAnagrafica(anagraficaProva.getIdAnagrafica())).thenReturn(listaPolizzeDefault);
    when(informazioniAnagraficaRepository.getAnagraficheDaIdAnagrafica(listaPolizzeDefault.get(0).getIdContraente())).thenReturn(listaAnagraficheDefault.get(0));



    List<DettaglioPolizzaProva> listaDettaglioFornitaDalService =informazioniPolizzaService.estraiEProcessaInformazioni(codiceFiscale);

    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getNumeroPolizza()).isEqualTo(1);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdContraente()).isEqualTo(9999);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdAssicurato()).isEqualTo(9999);
    Assertions.assertThat(listaDettaglioFornitaDalService.get(0).getPolizza().getIdBeneficiario()).isEqualTo(9999);
  }


}
