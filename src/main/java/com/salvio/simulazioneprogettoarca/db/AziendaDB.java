package com.salvio.simulazioneprogettoarca.db;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AziendaDB {

  private Integer pIvaAzienda;
  private String codiceFiscaleProprietario;
  private String nomeAzienda;
  private String nazione;
  private LocalDate dataFondazione;
}
