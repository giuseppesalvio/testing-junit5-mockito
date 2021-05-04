package com.roberto.outputPorts;

import com.roberto.entitys.HexagonalAnagrafica;
import java.util.List;

public interface RepositoryHexagonalAnagrafica {

  List<HexagonalAnagrafica> getAllAnagrafiche();
  HexagonalAnagrafica findById(Integer idAnagrafica);

  HexagonalAnagrafica updateHexAnagrafica(String sqlUpdateCommand);

  void deleteHexAnagrafica(Integer idAnagrafica);
}
