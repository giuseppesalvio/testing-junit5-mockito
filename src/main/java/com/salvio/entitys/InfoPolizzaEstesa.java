package com.salvio.entitys;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InfoPolizzaEstesa {

  private List<PolizzaEstesa> listaPolizzeCollegate;
  private int totalePremioDaVersare;

}
