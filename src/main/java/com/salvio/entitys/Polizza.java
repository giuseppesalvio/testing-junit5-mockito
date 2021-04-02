package com.salvio.entitys;

import lombok.Value;

@Value
public class Polizza {
    private Integer id;
    private Integer idContraente;
    private Integer idAssicurato;
    private Integer idBeneficiario;
}
