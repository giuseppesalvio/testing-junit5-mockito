package com.salvio.inputPorts;

import com.salvio.entitys.HexagonalPolizza;
import com.salvio.entitys.StatoPolizza;
import java.util.List;

public interface UseCasePolizza {

  //negli esempi nei parametri richiestri viene messo final e a volte anche @Valid, come mai?
  HexagonalPolizza getPolizzaByNumPolizza(Integer numeroPolizzaCercata);

  List<HexagonalPolizza> getAllPolizzas();

  StatoPolizza checkValiditaPolizza(Integer numeroPolizza);


  boolean sospendiPolizza(Integer numeroPolizza);


  boolean riattivaPolizza(Integer numeroPolizza);


  HexagonalPolizza modificaFigurePolizza(Integer numeroPolizza,Integer idContraente,Integer idAssicurato,Integer idBeneficiario);


}
