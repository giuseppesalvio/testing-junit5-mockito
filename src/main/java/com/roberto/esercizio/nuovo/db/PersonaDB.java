package com.roberto.esercizio.nuovo.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDB {
    int id;
    String nome;
    String cognome;
    String codiceFiscale;
    int indirizzo;
}
