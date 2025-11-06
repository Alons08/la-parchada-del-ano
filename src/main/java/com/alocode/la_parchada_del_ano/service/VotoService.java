package com.alocode.la_parchada_del_ano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.alocode.la_parchada_del_ano.model.Voto;
import com.alocode.la_parchada_del_ano.repository.VotoRepository;

@Service
@RequiredArgsConstructor
public class VotoService {
    
    private final VotoRepository votoRepository;
    
    public Voto guardarVoto(Voto voto) {
        return votoRepository.save(voto);
    }
    
}