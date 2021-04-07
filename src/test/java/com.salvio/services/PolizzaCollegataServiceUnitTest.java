package com.salvio.services;

import com.salvio.entitys.PolizzaCollegata;
import com.salvio.repository.PolizzaCollegataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PolizzaCollegataServiceUnitTest {


    @InjectMocks
    private PolizzaCollegataService polizzaCollegataService;

    @Mock
    private PolizzaCollegataRepository polizzaCollegataRepository;

    @Test
    public void chiamaEstraiPolizzaCollegataDaService(){
        List<PolizzaCollegata> listaPolizzeAspettate=new ArrayList<>();
        PolizzaCollegata polizzaCollegata=PolizzaCollegata.builder().numeroPolizza(1).idContraente(2).idAssicurato(2).idBeneficiario(2).build();
        PolizzaCollegata polizzaCollegata1=PolizzaCollegata.builder().numeroPolizza(2).idContraente(4).idAssicurato(2).idBeneficiario(3).build();
        listaPolizzeAspettate.add(polizzaCollegata);
        listaPolizzeAspettate.add(polizzaCollegata1);
        when(polizzaCollegataRepository.getPolizzaCollegataDaRepo(anyString())).thenReturn(listaPolizzeAspettate);
        List<PolizzaCollegata> listaPolizzeCollegateEffettivamenteRestituite =polizzaCollegataService.getPolizzaCollegataDaService("zzz");
        verify(polizzaCollegataRepository).getPolizzaCollegataDaRepo("zzz");


        Assertions.assertEquals(new Integer(1),listaPolizzeCollegateEffettivamenteRestituite.get(0).getNumeroPolizza());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegateEffettivamenteRestituite.get(0).getIdContraente());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegateEffettivamenteRestituite.get(0).getIdAssicurato());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegateEffettivamenteRestituite.get(0).getIdBeneficiario());

        Assertions.assertEquals(new Integer(2),listaPolizzeCollegateEffettivamenteRestituite.get(1).getNumeroPolizza());
        Assertions.assertEquals(new Integer(4),listaPolizzeCollegateEffettivamenteRestituite.get(1).getIdContraente());
        Assertions.assertEquals(new Integer(2),listaPolizzeCollegateEffettivamenteRestituite.get(1).getIdAssicurato());
        Assertions.assertEquals(new Integer(3),listaPolizzeCollegateEffettivamenteRestituite.get(1).getIdBeneficiario());

    }

}
