package com.alocode.la_parchada_del_ano.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "combate_id", nullable = false)
    private Combate combate;
    
    @ManyToOne
    @JoinColumn(name = "peleador_id", nullable = false)
    private Peleador peleador;
    
    @Column(name = "fecha_voto")
    private LocalDateTime fechaVoto = LocalDateTime.now();
    
    @Column(name = "usuario_ip", nullable = false)
    private String usuarioIp;
}