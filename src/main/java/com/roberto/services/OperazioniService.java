package com.roberto.services;

import com.roberto.entitys.PolizzaProva;
import com.roberto.repository.OperazioniRepository;
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
