package it.uniroma3.siw.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
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
	
	public List<Ricetta> findAllByOrderByNomeRicettaAsc() {
		return this.ricettaRepository.findAllByOrderByNomeRicettaAsc();
	}

	public Ricetta findById(Long id) {
		try {
			return this.ricettaRepository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public Ricetta findByNomeRicettaAndCuoco(String nomeRicetta, Cuoco cuoco) {
		try {
			return this.ricettaRepository.findByNomeRicettaAndCuoco(nomeRicetta, cuoco);
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public Ricetta save(Ricetta ricetta) {
		return this.ricettaRepository.save(ricetta);
	}

	public boolean existsByNomeRicettaAndCuoco(String nomeRicetta, Cuoco cuoco) {
		return this.ricettaRepository.existsByNomeRicettaAndCuoco(nomeRicetta, cuoco);
	}

	public void delete(Ricetta ricetta) {
		Ricetta del = this.ricettaRepository.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), ricetta.getCuoco());
		this.ricettaRepository.delete(del);
	}

	public boolean existsByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.existsByNomeRicetta(nomeRicetta);
	}

	public Ricetta findByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.findByNomeRicetta(nomeRicetta);
	}

	public Iterable<Ricetta> findAllByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.findAllByNomeRicetta(nomeRicetta);
	}

	
	public void insertRicettaIngredienteIntoRicettaIngrediente2Quantità(Integer quantity, Long ingredienteId, Long ricettaId) {
		this.ricettaRepository.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId, quantity);
		
	}
	
	public void deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(Long ingredienteId, Long ricettaId) {
		this.ricettaRepository.deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId);
	}
}
