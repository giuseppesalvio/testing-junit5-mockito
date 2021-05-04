package com.roberto.esercizio.nuovo.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndirizzoDB {
    String via;
    String citta;
    String provincia;
    String cap;
}
