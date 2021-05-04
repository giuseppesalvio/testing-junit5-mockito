package com.roberto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.roberto.entitys.AnagraficaProva;
import com.roberto.entitys.PolizzaProva;
import com.roberto.entitys.ProvaDettaglioPolizza;
import com.roberto.repository.AnagraficaRepositoryProva;
import com.roberto.repository.PolizzaRepositoryProva;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roberto.services.ProvaRicercaAnagraficheDiPolizzaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvaRicercaAnagraficheDiPolizzaServiceTest {

  @InjectMocks
  private ProvaRicercaAnagraficheDiPolizzaService provaRicercaAnagraficheDiPolizzaService;

  @Mock
  private AnagraficaRepositoryProva anagraficaRepositoryProva;

  @Mock
  private PolizzaRepositoryProva polizzaRepositoryProva;


  @Test
  public void controlloPathServiceOk(){


    String codiceFiscale="1234567890123456";
    AnagraficaProva anagraficaProvaDelCodiceFiscale= new AnagraficaProva(9999,"Mario","Rossi","1234567890123456");
    List<PolizzaProva> listaPolizze=new ArrayList<>();
    listaPolizze.add(new PolizzaProva(1,9999,9999,9999));
    listaPolizze.add(new PolizzaProva(2,9999,8888,8888));
    when(anagraficaRepositoryProva.getAnagraficaDaCodiceFiscale(codiceFiscale)).thenReturn(anagraficaProvaDelCodiceFiscale);
    when(polizzaRepositoryProva.ottieniListaPolizzeAssociateAIdAnagrafica(anagraficaProvaDelCodiceFiscale.getIdAnagrafica())).thenReturn(listaPolizze);




    List<ProvaDettaglioPolizza> lista= provaRicercaAnagraficheDiPolizzaService.ottieniPolizzeCollegateACodiceFiscaleFornito(codiceFiscale);

    Assertions.assertEquals(Optional.of(1),java.util.Optional.ofNullable(lista.get(0).getPolizzaProva().getNumeroPolizza()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(0).getContraente().getCodiceFiscale()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(0).getAssicurato().getCodiceFiscale()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(0).getBeneficiario().getCodiceFiscale()));

    Assertions.assertEquals(Optional.of(2),java.util.Optional.ofNullable(lista.get(1).getPolizzaProva().getNumeroPolizza()));
    Assertions.assertEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(1).getContraente().getCodiceFiscale()));
    Assertions.assertNotEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(1).getAssicurato().getCodiceFiscale()));
    Assertions.assertNotEquals(Optional.of(codiceFiscale),java.util.Optional.ofNullable(lista.get(1).getBeneficiario().getCodiceFiscale()));


  }

}
