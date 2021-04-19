package com.salvio.outputPorts;

import com.salvio.entitys.HexagonalAnagrafica;
import java.util.List;

public interface RepositoryHexagonalAnagrafica {

  List<HexagonalAnagrafica> getAllAnagrafiche();
  HexagonalAnagrafica findById(Integer idAnagrafica);

  HexagonalAnagrafica updateHexAnagrafica(String sqlUpdateCommand);

  void deleteHexAnagrafica(Integer idAnagrafica);
}
