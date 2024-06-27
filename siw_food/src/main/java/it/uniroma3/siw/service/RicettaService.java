package it.uniroma3.siw.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;

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
}
