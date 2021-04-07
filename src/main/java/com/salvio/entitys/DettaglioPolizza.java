package com.salvio.entitys;

import lombok.Value;

@Value
public class DettaglioPolizza {
    private Polizza polizza;        //---> id;idContraente;idAssicurato;idBeneficiario
    private Anagrafica assicurato;    //--->id;nome;cognome;codiceFiscale;
    private Anagrafica contraente;
    private Anagrafica beneficiario;
}
