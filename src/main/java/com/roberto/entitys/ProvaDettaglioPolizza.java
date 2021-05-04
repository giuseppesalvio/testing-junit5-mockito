package com.roberto.entitys;

import lombok.Value;

@Value
public class ProvaDettaglioPolizza {

  PolizzaProva polizzaProva;
  AnagraficaProva contraente;
  AnagraficaProva assicurato;
  AnagraficaProva beneficiario;



}
