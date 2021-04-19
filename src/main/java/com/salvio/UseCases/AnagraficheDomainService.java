package com.salvio.UseCases;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.salvio.inputPorts.AnagraficheDomainUseCase;
import com.salvio.outputPorts.LoadAnagraficaOutputPort;
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
