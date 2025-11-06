package com.alocode.la_parchada_del_ano.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alocode.la_parchada_del_ano.model.Combate;

import java.util.List;

@Repository
public interface CombateRepository extends JpaRepository<Combate, Long> {

    List<Combate> findByEsEstelarTrue();
    List<Combate> findByEsEstelarFalse();
    List<Combate> findAllByOrderByFechaCombateAsc();

}