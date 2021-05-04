package com.roberto.inputPorts;

import com.roberto.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import java.util.List;

public interface PolizzeAssociateDomainUseCase {

  List<PolizzaDomain> getListaPolizzeAssociate(Integer idAnagrafica);

}
