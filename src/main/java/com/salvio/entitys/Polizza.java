package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;


@Value
public class Polizza {
    private Integer id;
    private Integer idContraente;
    private Integer idAssicurato;
    private Integer idBeneficiario;
}
