package com.arca.entity;

import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Value
public class StatisticheAccesso {
    private String data;
    private String bancaSso;
    private Integer conteggioAccessi;

}
