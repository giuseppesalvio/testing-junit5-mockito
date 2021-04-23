package com.salvio.usecases;

import com.salvio.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import com.salvio.inputPorts.PolizzeAssociateDomainUseCase;
import com.salvio.outputPorts.LoadPolizzaOutputPort;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PolizzaDomainService implements PolizzeAssociateDomainUseCase {

  private LoadPolizzaOutputPort loadPolizzaOutputPort;
  @Override
  public List<PolizzaDomain> getListaPolizzeAssociate(Integer idAnagrafica){

    List<PolizzaDomain> lista= new ArrayList<>();
    lista=loadPolizzaOutputPort.getPolizzeAssociate(idAnagrafica);

    return lista;
  }

}
