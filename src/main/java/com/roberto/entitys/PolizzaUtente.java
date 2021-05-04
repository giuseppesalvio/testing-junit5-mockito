package com.roberto.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolizzaUtente
{
    private Long  id;
    private String numeroPolizza;
    private Long utenteid ;
}
