package com.salvio.DomainObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
public class DettaglioPolizzaDomain {

  @Getter
  private final PolizzaDomain polizza;
  @Getter
  private final AnagraficaDomain anagraficaContraente;
  @Getter
  private final AnagraficaDomain anagraficaAssicurato;
  @Getter
  private final AnagraficaDomain anagraficaBeneficiario;


  public DettaglioPolizzaDomain createDettaglio(PolizzaDomain polizza, AnagraficaDomain anagraficaContraente,
      AnagraficaDomain anagraficaAssicurato, AnagraficaDomain anagraficaBeneficiario) {

    return new DettaglioPolizzaDomain(polizza,anagraficaContraente,anagraficaAssicurato,anagraficaBeneficiario);
  }

  @Value
  public static class PolizzaDomain {

    private Integer numeroPolizza;
    private Integer idContraente;
    private Integer idAssicurato;
    private Integer idBeneficiario;
  }

  public PolizzaDomain createPolizza(Integer numeroPolizza,Integer idContraente, Integer idAssicurato, Integer idBeneficiario) {

    return new PolizzaDomain(numeroPolizza,idContraente,idAssicurato,idBeneficiario);
  }

  @Value
  public static class AnagraficaDomain {

    private Integer idAnagrafica;
    private String nome;
    private String cognome;
    private String codiceFiscale;
  }

  public AnagraficaDomain createAnagrafica(Integer idAnagrafica,String nome, String cognome,String codiceFiscale) {

    return new AnagraficaDomain(idAnagrafica,nome,cognome,codiceFiscale);
  }


}
