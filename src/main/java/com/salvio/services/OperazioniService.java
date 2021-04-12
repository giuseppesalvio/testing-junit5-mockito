package com.salvio.services;

import com.salvio.entitys.PolizzaProva;
import com.salvio.repository.OperazioniRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OperazioniService {

  private OperazioniRepository operazioniRepository;

  public OperazioniService(OperazioniRepository operazioniRepository) {
    this.operazioniRepository = operazioniRepository;
  }


  public PolizzaProva mostraPolizzaProvaRichiesta(int numeroPolizzaRichiesta) {

   return operazioniRepository.getPolizzaByNumeroPolizza(numeroPolizzaRichiesta);

  }
}
