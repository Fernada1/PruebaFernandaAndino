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

import com.fernandaAndino.app.entity.Docente;
import com.fernandaAndino.app.service.DocenteService;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {
	
	@Autowired
	private DocenteService docenteService;
	
	//crear un nuevo usuario
		@PostMapping
		public ResponseEntity<?> create (@RequestBody Docente docente){
			return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.save(docente));
		}
		// leer un usuario
		@GetMapping("/{id}")
		public ResponseEntity<?> read (@PathVariable(value = "id") Long docenteId){
			Optional<Docente> oDocente = docenteService.findById(docenteId);
			
			if (!oDocente.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(oDocente);
		}
		
		//actualizar un usuario
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Docente docenteDetails, @PathVariable(value = "id") Long docenteId){
			Optional<Docente> docente = docenteService.findById(docenteId);
			if(!docente.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			
			
			docente.get().setNombre(docenteDetails.getNombre());
			docente.get().setApellido(docenteDetails.getApellido());
			docente.get().setCelular(docenteDetails.getCelular());
			docente.get().setEmail(docenteDetails.getEmail());
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.save(docente.get()));
			}
		
		//Borrar un usuario
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long docenteId){
			if(!docenteService.findById(docenteId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			docenteService.deleteById(docenteId);
			return ResponseEntity.ok().build();
		}
		
		
		
		//leer todos los usuarios
		
		@GetMapping
		public List<Docente> readAll(){
			List<Docente> docentes = StreamSupport
					.stream(docenteService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			return docentes;
		}
}
