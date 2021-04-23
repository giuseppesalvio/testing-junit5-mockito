package com.salvio.outputPorts;

import com.salvio.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import java.util.List;

public interface LoadPolizzaOutputPort {

  List<PolizzaDomain> getPolizzeAssociate(Integer idAnagrafica);


}
