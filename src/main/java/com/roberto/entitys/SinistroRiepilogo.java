package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SinistroRiepilogo {

  private String targaA;
  private String targaB;
  private String assicurazioneA;
  private String assicurazioneB;
  private String dataSinistro;
}
