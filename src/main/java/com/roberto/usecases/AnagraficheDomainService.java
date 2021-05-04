package com.roberto.usecases;

import com.roberto.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.roberto.inputPorts.AnagraficheDomainUseCase;
import com.roberto.outputPorts.LoadAnagraficaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnagraficheDomainService implements AnagraficheDomainUseCase {

  private LoadAnagraficaOutputPort loadAnagraficaPort;

  @Override
  public AnagraficaDomain getAnagraficaDaCodiceFiscale(String codiceFiscale){

    AnagraficaDomain anagraficaCollegata=null;

    if (loadAnagraficaPort.isValid(codiceFiscale)) {
      anagraficaCollegata= loadAnagraficaPort.getAnagraficaBy(codiceFiscale);
    }
    return anagraficaCollegata;
  }

}
