package com.roberto.outputPorts;

import com.roberto.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;

public interface LoadAnagraficaOutputPort {

  AnagraficaDomain getAnagraficaBy(String codiceFiscale);

  boolean isValid(String codiceFiscale);
}
