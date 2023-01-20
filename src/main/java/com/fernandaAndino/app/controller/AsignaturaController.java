package com.fernandaAndino.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandaAndino.app.entity.Asignatura;
import com.fernandaAndino.app.service.AsignaturaService;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {
	@Autowired
	private AsignaturaService asignaturaService;
	
	//crear un nuevo usuario
		@PostMapping
		public ResponseEntity<?> create (@RequestBody Asignatura asignatura){
			return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.save(asignatura));
		}
		// leer un usuario
		@GetMapping("/{id}")
		public ResponseEntity<?> read (@PathVariable(value = "id") Long asignaturaId){
			Optional<Asignatura> oAsignatura = asignaturaService.findById(asignaturaId);
			
			if (!oAsignatura.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(oAsignatura);
		}
		
		//actualizar un usuario
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Asignatura asigDetails, @PathVariable(value = "id") Long asignaturaId){
			Optional<Asignatura> asignatura = asignaturaService.findById(asignaturaId);
			if(!asignatura.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			
	
			asignatura.get().setNombre(asigDetails.getNombre());
			asignatura.get().setCarrera(asigDetails.getCarrera());
			asignatura.get().setHora_ini(asigDetails.getHora_ini());
			asignatura.get().setHora_fin(asigDetails.getHora_fin());
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.save(asignatura.get()));
			}
		
		//Borrar un usuario
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long asignaturaId){
			if(!asignaturaService.findById(asignaturaId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			asignaturaService.deleteById(asignaturaId);
			return ResponseEntity.ok().build();
		}
		
		
		
		//leer todos los usuarios
		
		@GetMapping
		public List<Asignatura> readAll(){
			List<Asignatura> asignaturas = StreamSupport
					.stream(asignaturaService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			return asignaturas;
		}
}
