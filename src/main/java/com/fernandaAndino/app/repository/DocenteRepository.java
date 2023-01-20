package com.fernandaAndino.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandaAndino.app.entity.Docente;


@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long>{
	
}
