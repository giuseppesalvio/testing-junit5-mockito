package com.salvio.entitys;

import lombok.Value;

@Value
public class DettaglioPolizzaProva {
  private PolizzaProva polizza;        //---> id;idContraente;idAssicurato;idBeneficiario
  private AnagraficaProva contraente;    //--->id;nome;cognome;codiceFiscale;
  private AnagraficaProva assicurato;
  private AnagraficaProva beneficiario;
}
