package com.arca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticheAccessoBS {

    private String data;
    private String bancaSso;
    private Integer conteggioAccessi;
}
