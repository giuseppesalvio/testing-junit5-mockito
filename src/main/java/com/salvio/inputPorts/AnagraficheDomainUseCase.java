package com.salvio.inputPorts;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

public interface AnagraficheDomainUseCase {




  AnagraficaDomain getAnagraficaDaCodiceFiscale(String codiceFiscale);

  }

