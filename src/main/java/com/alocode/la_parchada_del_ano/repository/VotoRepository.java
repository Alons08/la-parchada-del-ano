package com.alocode.la_parchada_del_ano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alocode.la_parchada_del_ano.model.Voto;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByCombateIdAndUsuarioIp(Long combateId, String usuarioIp);
    Long countByCombateIdAndPeleadorId(Long combateId, Long peleadorId);
    
}