package com.roberto.service;

import static org.mockito.Mockito.when;

import com.roberto.entitys.PolizzaProva;
import com.roberto.repository.OperazioniRepository;
import com.roberto.services.OperazioniService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperazioniServiceUnitTest {

  @InjectMocks
  private OperazioniService operazioniService;

  @Mock
  private OperazioniRepository operazioniRepository;

  @Test
  public void controlloExecuteOperazioniService(){
    String contentJsonStringRequest = "{\n"
        + "   \"idOperazione\":1,\n"
        + "   \"numeroPolizzaInteressata\":1,\n"
        + "   \"idAnagraficaInteressata\":9999\n"
        + "}";

    PolizzaProva polizzaProva= new PolizzaProva(4,8888,8888,8888);

    when(operazioniRepository.getPolizzaByNumeroPolizza(4)).thenReturn(polizzaProva);

    PolizzaProva polizzaRestituita = operazioniService.mostraPolizzaProvaRichiesta(4);

    Assertions.assertThat(polizzaRestituita.getIdContraente()).isEqualTo(8888);

  }

}
