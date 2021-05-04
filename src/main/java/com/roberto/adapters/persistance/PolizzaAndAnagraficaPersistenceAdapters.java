package com.roberto.adapters.persistance;

import com.roberto.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.roberto.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import com.roberto.outputPorts.LoadAnagraficaOutputPort;
import com.roberto.outputPorts.LoadPolizzaOutputPort;
import com.roberto.repository.AnagraficaDomainRepository;
import com.roberto.repository.PolizzaDomainRepository;

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
