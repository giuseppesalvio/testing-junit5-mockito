package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Azienda {

private String codiceAzienda;
private String nomeAzienda;
private String codiceFiscaleProprietarioAzienda;
private String dataFondazione;
private String nazione;


}
