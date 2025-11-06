package com.alocode.la_parchada_del_ano.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alocode.la_parchada_del_ano.model.Combate;
import com.alocode.la_parchada_del_ano.model.Voto;
import com.alocode.la_parchada_del_ano.service.CombateService;
import com.alocode.la_parchada_del_ano.service.VotoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    
    private final CombateService combateService;
    private final VotoService votoService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<Combate> combatesEstelares = combateService.obtenerCombatesEstelares();
        List<Combate> combatesSecundarios = combateService.obtenerCombatesSecundarios();

        // Cargar estadísticas iniciales
        Map<Long, Map<String, Object>> estadisticasCombates = combateService.obtenerEstadisticasTodosCombates();

        model.addAttribute("combatesEstelares", combatesEstelares);
        model.addAttribute("combatesSecundarios", combatesSecundarios);
        model.addAttribute("estadisticasCombates", estadisticasCombates);

        return "index";
    }
    
    @PostMapping("/votar")
    @ResponseBody
    public ResponseEntity<String> votar(@RequestParam Long combateId, 
                                       @RequestParam Long peleadorId,
                                       HttpServletRequest request) {
        
        String usuarioIp = obtenerDireccionIp(request);
        
        // Verificar si el usuario ya votó en este combate
        if (combateService.usuarioYaVoto(combateId, usuarioIp)) {
            return ResponseEntity.badRequest().body("Ya has votado en este combate");
        }
        
        try {
            // Crear y guardar el voto
            Voto voto = new Voto();
            voto.setCombate(combateService.obtenerCombatePorId(combateId));
            voto.setPeleador(combateService.obtenerPeleadorPorId(peleadorId));
            voto.setUsuarioIp(usuarioIp);
            voto.setFechaVoto(LocalDateTime.now());
            
            votoService.guardarVoto(voto);
            
            return ResponseEntity.ok("Voto registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el voto: " + e.getMessage());
        }
    }
    
    // NUEVO ENDPOINT: Obtener estadísticas en formato JSON
    @GetMapping("/estadisticas/{combateId}")
    @ResponseBody
    public Map<String, Object> obtenerEstadisticas(@PathVariable Long combateId) {
        return combateService.obtenerEstadisticasCombate(combateId);
    }
    
    // NUEVO ENDPOINT: Obtener estadísticas de todos los combates
    @GetMapping("/estadisticas")
    @ResponseBody
    public Map<Long, Map<String, Object>> obtenerTodasEstadisticas() {
        return combateService.obtenerEstadisticasTodosCombates();
    }
    
    private String obtenerDireccionIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // Si hay varias IPs, toma la primera
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

        // Endpoint para obtener datos de un peleador por su ID
        @GetMapping("/peleador/{id}")
        @ResponseBody
        public ResponseEntity<com.alocode.la_parchada_del_ano.model.Peleador> obtenerPeleador(@PathVariable Long id) {
            try {
                com.alocode.la_parchada_del_ano.model.Peleador peleador = combateService.obtenerPeleadorPorId(id);
                return ResponseEntity.ok(peleador);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
    }
}