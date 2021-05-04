package com.roberto.outputPorts;

import com.roberto.entitys.HexagonalPolizza;
import java.util.List;
import java.util.Optional;

public interface RepositoryHexagonalPolizza {

  List<HexagonalPolizza> findAll();
  Optional<HexagonalPolizza> findByNumPolizza(Integer numeroPolizza);

  HexagonalPolizza updateHexPolizza(String sqlCommandUpdate);
  void deleteHexPolizza(Integer numeroPolizza);

}
