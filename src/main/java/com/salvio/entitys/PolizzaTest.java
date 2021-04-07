package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PolizzaTest {

    private Integer numeroPolizza;
    private String contraente;
    private String assicurato;
    private String beneficiario;
}
