package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolizzaEstesa {

private int numeroPolizza;
private int idContraente;
private int idAssicurato;
private int idBeneficiario;
private String dataProxQuietanzamento;
private double importoQuietanzamento;


}
