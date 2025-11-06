package com.alocode.la_parchada_del_ano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.alocode.la_parchada_del_ano.model.Combate;
import com.alocode.la_parchada_del_ano.model.Peleador;
import com.alocode.la_parchada_del_ano.repository.CombateRepository;
import com.alocode.la_parchada_del_ano.repository.PeleadorRepository;
import com.alocode.la_parchada_del_ano.repository.VotoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CombateService {
    
    private final CombateRepository combateRepository;
    private final PeleadorRepository peleadorRepository;
    private final VotoRepository votoRepository;
    
    public List<Combate> obtenerCombatesEstelares() {
        return combateRepository.findByEsEstelarTrue();
    }
    
    public List<Combate> obtenerCombatesSecundarios() {
        return combateRepository.findByEsEstelarFalse();
    }
    
    public List<Combate> obtenerTodosCombates() {
        return combateRepository.findAllByOrderByFechaCombateAsc();
    }
    
    public boolean usuarioYaVoto(Long combateId, String usuarioIp) {
        return votoRepository.findByCombateIdAndUsuarioIp(combateId, usuarioIp).isPresent();
    }
    
    public Long obtenerTotalVotosPorPeleador(Long combateId, Long peleadorId) {
        return votoRepository.countByCombateIdAndPeleadorId(combateId, peleadorId);
    }
    
    public Combate obtenerCombatePorId(Long id) {
        return combateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combate no encontrado"));
    }
    
    public Peleador obtenerPeleadorPorId(Long id) {
        return peleadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peleador no encontrado"));
    }
    
    // NUEVO MÉTODO: Obtener estadísticas reales de votos para un combate
    public Map<String, Object> obtenerEstadisticasCombate(Long combateId) {
        Map<String, Object> estadisticas = new HashMap<>();
        try {
            Combate combate = obtenerCombatePorId(combateId);
            Long votosPeleador1 = votoRepository.countByCombateIdAndPeleadorId(combateId, combate.getPeleador1().getId());
            Long votosPeleador2 = votoRepository.countByCombateIdAndPeleadorId(combateId, combate.getPeleador2().getId());
            Long totalVotos = votosPeleador1 + votosPeleador2;
            double porcentaje1 = totalVotos > 0 ? (votosPeleador1.doubleValue() / totalVotos.doubleValue()) * 100 : 50.0;
            double porcentaje2 = totalVotos > 0 ? (votosPeleador2.doubleValue() / totalVotos.doubleValue()) * 100 : 50.0;
            estadisticas.put("votosPeleador1", votosPeleador1);
            estadisticas.put("votosPeleador2", votosPeleador2);
            estadisticas.put("totalVotos", totalVotos);
            estadisticas.put("porcentaje1", Math.round(porcentaje1 * 10.0) / 10.0);
            estadisticas.put("porcentaje2", Math.round(porcentaje2 * 10.0) / 10.0);
            estadisticas.put("peleador1Id", combate.getPeleador1().getId());
            estadisticas.put("peleador2Id", combate.getPeleador2().getId());
        } catch (Exception e) {
            estadisticas.put("votosPeleador1", 0L);
            estadisticas.put("votosPeleador2", 0L);
            estadisticas.put("totalVotos", 0L);
            estadisticas.put("porcentaje1", 50.0);
            estadisticas.put("porcentaje2", 50.0);
            estadisticas.put("peleador1Id", 0L);
            estadisticas.put("peleador2Id", 0L);
        }
        return estadisticas;
    }
    
    // NUEVO MÉTODO: Obtener estadísticas para todos los combates
    public Map<Long, Map<String, Object>> obtenerEstadisticasTodosCombates() {
        Map<Long, Map<String, Object>> todasEstadisticas = new HashMap<>();
        List<Combate> todosCombates = obtenerTodosCombates();
        
        for (Combate combate : todosCombates) {
            todasEstadisticas.put(combate.getId(), obtenerEstadisticasCombate(combate.getId()));
        }
        
        return todasEstadisticas;
    }
}