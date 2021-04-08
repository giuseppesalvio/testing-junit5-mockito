package com.salvio.entitys;

import lombok.Value;

@Value
public class DettaglioPolizzaProva {
  private PolizzaProva polizza;        //---> id;idContraente;idAssicurato;idBeneficiario
  private AnagraficaProva assicurato;    //--->id;nome;cognome;codiceFiscale;
  private AnagraficaProva contraente;
  private AnagraficaProva beneficiario;
}
