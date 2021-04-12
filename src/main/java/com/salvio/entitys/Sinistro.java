package com.salvio.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Sinistro {

  private String targaA;
  private String targaB;
  private String assicurazioneA;
  private String assicurazioneB;
  private String dataSinistro;

}
