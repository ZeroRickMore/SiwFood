package it.uniroma3.siw.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;
import jakarta.validation.Valid;

@Service
public class IngredienteService {

	/*##############################################################*/
	/*#########################REPOSITORY###########################*/
	/*##############################################################*/
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
	
	public Iterable<Ingrediente> findAll() {
		return this.ingredienteRepository.findAll();
	}

	public Ingrediente findById(Long id) {
		try {
			return this.ingredienteRepository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public boolean existsByNome(String nome) {
		return this.ingredienteRepository.existsByNome(nome);
	}

	public Ingrediente findByNome(String nome) {
		return this.ingredienteRepository.findByNome(nome);
	}

	public Ingrediente save(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}
	
	public void delete(Ingrediente ingrediente) {
		ingrediente = this.ingredienteRepository.findByNome(ingrediente.getNome());

		try { this.ingredienteRepository.deleteRowsWithIngredienteFromRicettaIngrediente2Quantità(ingrediente.getId()); }
		catch (Exception e) {} //Se l'ingrediente non è associato a nessuna ricetta, se provo a rimuovere una row alza un'exception, ma va bene cosi.
		
		this.ingredienteRepository.delete(ingrediente);
	}
	
	
}
