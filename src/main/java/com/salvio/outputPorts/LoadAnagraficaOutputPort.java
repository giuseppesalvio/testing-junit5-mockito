package com.salvio.outputPorts;

import com.salvio.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;

public interface LoadAnagraficaOutputPort {

  AnagraficaDomain getAnagraficaBy(String codiceFiscale);

  boolean isValid(String codiceFiscale);
}
