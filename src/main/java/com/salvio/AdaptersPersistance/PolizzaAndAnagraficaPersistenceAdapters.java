package com.salvio.AdaptersPersistance;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.salvio.DomainObjects.DettaglioPolizzaDomain.PolizzaDomain;
import com.salvio.outputPorts.LoadAnagraficaOutputPort;
import com.salvio.outputPorts.LoadPolizzaOutputPort;
import com.salvio.repository.AnagraficaDomainRepository;
import com.salvio.repository.AnagraficaRepository;
import com.salvio.repository.PolizzaDomainRepository;
import com.salvio.repository.PolizzaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class PolizzaAndAnagraficaPersistenceAdapters implements LoadAnagraficaOutputPort, LoadPolizzaOutputPort {

  private AnagraficaDomainRepository anagraficaDomainRepository;
  private PolizzaDomainRepository polizzaDomainRepository;
  @Override
 public  List<PolizzaDomain> getPolizzeAssociate(Integer idAnagrafica){

   return polizzaDomainRepository.cercaByCodiceAnagrafica(idAnagrafica);
  }

  @Override
  public AnagraficaDomain getAnagraficaBy(String codiceFiscale){

    return anagraficaDomainRepository.ottieniAnagraficaAttraversoCodiceFiscale(codiceFiscale);
  }

  @Override
   public boolean isValid(String codiceFiscale){

    return anagraficaDomainRepository.check(codiceFiscale);
  }
}
