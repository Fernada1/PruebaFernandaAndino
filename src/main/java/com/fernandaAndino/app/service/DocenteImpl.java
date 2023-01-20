package com.fernandaAndino.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandaAndino.app.entity.Docente;
import com.fernandaAndino.app.repository.DocenteRepository;



@Service
public class DocenteImpl implements DocenteService {
	
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Docente> findAll() {
		return docenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Docente> findAll(Pageable pageable) {
		return docenteRepository.findAll(pageable);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Docente> findById(Long id) {
		return docenteRepository.findById(id);
	}

	@Override
	@Transactional
	public Docente save(Docente docente) {
		return docenteRepository.save(docente);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		docenteRepository.deleteById(id);
		
	}

}
