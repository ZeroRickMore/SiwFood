package it.uniroma3.siw.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;
import jakarta.validation.Valid;

@Service
public class RicettaService {
	
	/*##############################################################*/
	/*#########################REPOSITORY###########################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaRepository ricettaRepository;

	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
	
	public Iterable<Ricetta> findAll() {
		return this.ricettaRepository.findAll();
	}

	public Ricetta findById(Long id) {
		try {
			return this.ricettaRepository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public Ricetta findByNomeAndCuoco(String nome, Cuoco cuoco) {
		return this.ricettaRepository.findByNomeAndCuoco(nome, cuoco);
	}

	public Ricetta save(Ricetta ricetta) {
		return this.ricettaRepository.save(ricetta);	
	}

	public boolean existsByNomeAndCuoco(String nome, Cuoco cuoco) {
		return this.ricettaRepository.existsByNomeAndCuoco(nome, cuoco);
	}
}
