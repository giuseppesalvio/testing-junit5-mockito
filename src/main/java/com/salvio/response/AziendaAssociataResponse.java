package com.salvio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AziendaAssociataResponse {


    private String codiceAzienda;
    private String nomeAzienda;
    private String nomeProprietario;
    private String cognomeProprietario;
    private String dataFondazione;
    private String nazione;
    private Integer numeroDipendenti;
    private Double monteStipendiTotale;

}
