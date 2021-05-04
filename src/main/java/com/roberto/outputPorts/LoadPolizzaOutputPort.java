package com.roberto.outputPorts;

import com.roberto.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import java.util.List;

public interface LoadPolizzaOutputPort {

  List<PolizzaDomain> getPolizzeAssociate(Integer idAnagrafica);


}
