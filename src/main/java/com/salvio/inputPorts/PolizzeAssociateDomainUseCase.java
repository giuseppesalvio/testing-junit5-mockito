package com.salvio.inputPorts;

import com.salvio.domain.objects.DettaglioPolizzaDomain.PolizzaDomain;
import java.util.List;

public interface PolizzeAssociateDomainUseCase {

  List<PolizzaDomain> getListaPolizzeAssociate(Integer idAnagrafica);

}
