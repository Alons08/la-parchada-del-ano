package com.alocode.la_parchada_del_ano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alocode.la_parchada_del_ano.model.Peleador;

import java.util.List;

@Repository
public interface PeleadorRepository extends JpaRepository<Peleador, Long> {

    List<Peleador> findByActivoTrue();
    
}