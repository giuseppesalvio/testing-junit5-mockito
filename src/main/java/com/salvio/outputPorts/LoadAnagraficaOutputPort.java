package com.salvio.outputPorts;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.AnagraficaDomain;

public interface LoadAnagraficaOutputPort {

  AnagraficaDomain getAnagraficaBy(String codiceFiscale);

  boolean isValid(String codiceFiscale);
}
