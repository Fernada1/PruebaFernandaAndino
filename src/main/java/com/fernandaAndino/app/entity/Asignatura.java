package com.fernandaAndino.app.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="asignaturas")
public class Asignatura implements Serializable{
	private static final long serialVersionUID = 4168056295676872556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_asignatura;
	
	@Column(length = 50)
	private String nombre;
	private String carrera;
	private String hora_ini;
	private String hora_fin;
	
	
	
	public Long getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(Long id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getHora_ini() {
		return hora_ini;
	}
	public void setHora_ini(String hora_ini) {
		this.hora_ini = hora_ini;
	}
	public String getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}
	
	@OneToMany(mappedBy="asignatura")
	   List<Docente> docente;
	 
	
}
