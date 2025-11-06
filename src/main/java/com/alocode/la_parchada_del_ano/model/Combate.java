package com.alocode.la_parchada_del_ano.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "combates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @ManyToOne
    @JoinColumn(name = "peleador1_id", nullable = false)
    private Peleador peleador1;
    
    @ManyToOne
    @JoinColumn(name = "peleador2_id", nullable = false)
    private Peleador peleador2;
    
    @Column(name = "fecha_combate")
    private LocalDateTime fechaCombate;
    
    @Column(name = "hora_combate")
    private String horaCombate;
    
    @Column(name = "es_estelar")
    private Boolean esEstelar = false;
    
    @ManyToOne
    @JoinColumn(name = "ganador_id")
    private Peleador ganador;
}