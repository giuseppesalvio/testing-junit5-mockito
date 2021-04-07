package com.salvio.controller;

import com.salvio.entitys.PolizzaCollegata;
import com.salvio.services.PolizzaCollegataService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PolizzaCollegataControllerUnitTest {

  @InjectMocks
  private PolizzaCollegataController polizzaCollegataController;

  @Mock
  private PolizzaCollegataService polizzaCollegataService;


  @Test
  public void chiamaEstraiPolizzeCollegateController() {

    String codiceFiscale = "zzz";
    List<PolizzaCollegata> listaPolizze = getPolizzeCollegate(1, 2, 9999);
    when(polizzaCollegataService.getPolizzaCollegataDaService(codiceFiscale))
        .thenReturn(listaPolizze);

    List<PolizzaCollegata> result = polizzaCollegataController.getPolizzeCollegateDaController(codiceFiscale);

    assertEquals(new Integer(1), result.get(0).getNumeroPolizza());
    assertEquals(new Integer(2), result.get(0).getIdContraente());
    assertEquals(new Integer(2), result.get(0).getIdAssicurato());
    assertEquals(new Integer(2), result.get(0).getIdBeneficiario());

    assertEquals(new Integer(2), result.get(1).getNumeroPolizza());
    assertEquals(new Integer(4), result.get(1).getIdContraente());
    assertEquals(new Integer(2), result.get(1).getIdAssicurato());
    assertEquals(new Integer(3), result.get(1).getIdBeneficiario());


  }

  private List<PolizzaCollegata> getPolizzeCollegate(int numeroPolizza1, int numeroPolizza2, int idAnagrafica) {
    List<PolizzaCollegata> listaPolizzeAspettate = new ArrayList<>();
    PolizzaCollegata polizzaCollegata = PolizzaCollegata.builder().numeroPolizza(numeroPolizza1).idContraente(idAnagrafica)
        .idAssicurato(idAnagrafica).idBeneficiario(idAnagrafica).build();
    PolizzaCollegata polizzaCollegata1 = PolizzaCollegata.builder().numeroPolizza(numeroPolizza2).idContraente(444)
        .idAssicurato(idAnagrafica).idBeneficiario(8888).build();
    listaPolizzeAspettate.add(polizzaCollegata);
    listaPolizzeAspettate.add(polizzaCollegata1);
    return listaPolizzeAspettate;
  }


}
