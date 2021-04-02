package com.salvio.entitys;

import lombok.Value;

@Value
public class DettaglioPolizza {
    private Polizza polizza;
    private Anagrafica assicurato;
    private Anagrafica contraente;
    private Anagrafica beneficiario;
}
