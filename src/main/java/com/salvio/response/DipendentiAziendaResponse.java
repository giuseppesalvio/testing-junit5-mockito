package com.salvio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DipendentiAziendaResponse {


  private Integer idDipendente;
  private String nome;
  private String cognome;
  private String codiceFiscale;
  private Double stipendio;


}
