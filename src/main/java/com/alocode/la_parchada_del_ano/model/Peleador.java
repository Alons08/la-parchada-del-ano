package com.alocode.la_parchada_del_ano.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "peleadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peleador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Column(nullable = false)
    private String apodo;
    
    @Column(length = 1000)
    private String descripcion;
    
    private Double peso;
    
    private Integer edad;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    
    @Column(name = "record_ganadas")
    private Integer recordGanadas = 0;
    
    @Column(name = "record_perdidas")
    private Integer recordPerdidas = 0;
    
    @Column(name = "record_empates")
    private Integer recordEmpates = 0;
    
        @Column(name = "chala")
        private Integer chala = 0;
}