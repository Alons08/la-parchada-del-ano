package com.alocode.la_parchada_del_ano.model;

import lombok.Data;

@Data
public class CombateDTO {
    private Long id;
    private String titulo;
    private Peleador peleador1;
    private Peleador peleador2;
    private String categoria;
    private Boolean esEstelar;
    private Long totalVotos;
    private Integer porcentajeVotos1;
    private Integer porcentajeVotos2;

}