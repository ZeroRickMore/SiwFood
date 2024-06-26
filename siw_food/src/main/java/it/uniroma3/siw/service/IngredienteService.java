package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {

	/*##############################################################*/
	/*#########################REPOSITORY###########################*/
	/*##############################################################*/
	
	@Autowired
	private IngredienteRepository IngredienteRepository;
	
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
	
	public Iterable<Ingrediente> findAll() {
		return this.IngredienteRepository.findAll();
	}

	public Ingrediente findById(Long id) {
		return this.IngredienteRepository.findById(id).get();
	}
	
	
}
