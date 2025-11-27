package com.alocode.la_parchada_del_ano.config;

import com.alocode.la_parchada_del_ano.model.Combate;
import com.alocode.la_parchada_del_ano.model.Peleador;
import com.alocode.la_parchada_del_ano.repository.CombateRepository;
import com.alocode.la_parchada_del_ano.repository.PeleadorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {

	private final PeleadorRepository peleadorRepository;
	private final CombateRepository combateRepository;

	@PostConstruct
	public void init() {
		if (peleadorRepository.count() == 0) {
			// Crear peleadores
			Peleador peleador1 = new Peleador();
			peleador1.setNombre("Shuls");
			peleador1.setApodo("Toro");
			peleador1.setDescripcion("Campero profesional y padre de Aitana.");
			peleador1.setPeso(75.0);
			peleador1.setEdad(28);
			peleador1.setNombreImagen("shuls.jpg");
			peleador1.setRecordGanadas(20);
			peleador1.setRecordPerdidas(2);
			peleador1.setActivo(true);

			Peleador peleador2 = new Peleador();
			peleador2.setNombre("Carlos Waller");
			peleador2.setApodo("Pantene");
			peleador2.setDescripcion("Conocido por tener más panza que p3n3.");
			peleador2.setPeso(74.5);
			peleador2.setEdad(26);
			peleador2.setNombreImagen("carlos_waller.jpg");
			peleador2.setRecordGanadas(18);
			peleador2.setRecordPerdidas(3);
			peleador2.setActivo(true);

			Peleador peleador3 = new Peleador();
			peleador3.setNombre("Luis Torres");
			peleador3.setApodo("El Toro");
			peleador3.setDescripcion("Gran resistencia y fuerza física.");
			peleador3.setPeso(80.0);
			peleador3.setEdad(30);
			peleador3.setNombreImagen("luis_torres.jpg");
			peleador3.setRecordGanadas(15);
			peleador3.setRecordPerdidas(5);
			peleador3.setActivo(true);

			Peleador peleador4 = new Peleador();
			peleador4.setNombre("Miguel Díaz");
			peleador4.setApodo("El Águila");
			peleador4.setDescripcion("Técnico y estratégico en el ring.");
			peleador4.setPeso(78.0);
			peleador4.setEdad(27);
			peleador4.setNombreImagen("miguel_diaz.jpg");
			peleador4.setRecordGanadas(12);
			peleador4.setRecordPerdidas(7);
			peleador4.setActivo(true);

			peleadorRepository.save(peleador1);
			peleadorRepository.save(peleador2);
			peleadorRepository.save(peleador3);
			peleadorRepository.save(peleador4);

			// Crear pelea estelar
			Combate estelar = new Combate();
			estelar.setTitulo("Pelea Estelar: Shuls vs Waller");
			estelar.setPeleador1(peleador1);
			estelar.setPeleador2(peleador2);
			estelar.setFechaCombate(LocalDateTime.of(2026, 2, 14, 20, 0));
			estelar.setHoraCombate("20:00");
			estelar.setEsEstelar(true);
			combateRepository.save(estelar);

			// Crear pelea secundaria
			Combate secundaria = new Combate();
			secundaria.setTitulo("Pelea Secundaria: El Toro vs El Águila");
			secundaria.setPeleador1(peleador3);
			secundaria.setPeleador2(peleador4);
			secundaria.setFechaCombate(LocalDateTime.of(2026, 2, 14, 18, 0));
			secundaria.setHoraCombate("18:00");
			secundaria.setEsEstelar(false);
			combateRepository.save(secundaria);
		}
	}
}
