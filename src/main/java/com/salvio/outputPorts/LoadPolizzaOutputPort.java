package com.salvio.outputPorts;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.PolizzaDomain;
import java.util.List;

public interface LoadPolizzaOutputPort {

  List<PolizzaDomain> getPolizzeAssociate(Integer idAnagrafica);


}
