package com.salvio.inputPorts;

import com.salvio.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;

public interface AnagraficheDomainUseCase {




  AnagraficaDomain getAnagraficaDaCodiceFiscale(String codiceFiscale);

  }

