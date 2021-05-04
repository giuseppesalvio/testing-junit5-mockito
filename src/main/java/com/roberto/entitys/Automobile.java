package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Automobile {

 private String numeroTarga;
 private String codiceFiscaleProprietario;
 private String P_IvaAssicurazioneAssociata;
 private Integer numeroPolizzaAssociata;



}
