package com.salvio.Adapters;

import com.salvio.DomainObjects.DettaglioPolizzaDomain.AnagraficaDomain;
import com.salvio.DomainObjects.DettaglioPolizzaDomain.PolizzaDomain;
import com.salvio.inputPorts.AnagraficheDomainUseCase;
import com.salvio.inputPorts.PolizzeAssociateDomainUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendCodiceFiscaleController {

  private PolizzeAssociateDomainUseCase polizzeAssociateDomainUseCase;

  private AnagraficheDomainUseCase anagraficheDomainUseCase;


  @PostMapping(path="/users/send/{codiceFiscale}")
  public List<PolizzaDomain> sendInfo(@PathVariable("codiceFiscale")String codiceFiscale){


    AnagraficaDomain anagrafica = anagraficheDomainUseCase.getAnagraficaDaCodiceFiscale(codiceFiscale);
    return polizzeAssociateDomainUseCase.getListaPolizzeAssociate(anagrafica.getIdAnagrafica());



  }


}