package com.roberto.entitys;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InfoPolizzaCompletaRiepilogo {

  private List<RiepilogoPolizzaCompleta> listaPolizze;
  private Double totaleImportoDaVersare;


}
