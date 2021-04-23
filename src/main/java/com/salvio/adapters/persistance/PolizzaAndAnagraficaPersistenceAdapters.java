package com.salvio.adapters.persistance;

import com.salvio.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.salvio.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import com.salvio.outputPorts.LoadAnagraficaOutputPort;
import com.salvio.outputPorts.LoadPolizzaOutputPort;
import com.salvio.repository.AnagraficaDomainRepository;
import com.salvio.repository.PolizzaDomainRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
