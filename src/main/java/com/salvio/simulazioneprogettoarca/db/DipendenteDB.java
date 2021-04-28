package com.salvio.simulazioneprogettoarca.db;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class DipendenteDB {

  private Integer pIvaAzienda;
  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String ruolo;
  private Double stipendio;
  private LocalDate dataAssunzione;


}
