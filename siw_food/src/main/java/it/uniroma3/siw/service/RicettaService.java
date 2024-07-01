package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;
import jakarta.validation.Valid;

@Service
public class RicettaService {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private RicettaRepository ricettaRepository;

	
	
	
//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
//=======================================================================================================\\

					

					
	/*===============================================================================================*/
	/*                                       SAVE DELETE METHODS                                     */
	/*===============================================================================================*/



	
	public Ricetta save(Ricetta ricetta) {
		return this.ricettaRepository.save(ricetta);
	}
	
	
	public void insertRicettaIngredienteIntoRicettaIngrediente2Quantità(Integer quantity, Long ingredienteId, Long ricettaId) {
		this.ricettaRepository.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId, quantity);
		
	}
	
	
	public void delete(Ricetta ricetta) {
		Ricetta del = this.ricettaRepository.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), ricetta.getCuoco());
		this.ricettaRepository.delete(del);
	}

	
	public void deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(Long ingredienteId, Long ricettaId) {
		this.ricettaRepository.deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId);
	}
	
	
	
	
	/*===============================================================================================*/
	/*                                         EXISTS METHODS                                        */
	/*===============================================================================================*/


	
	
	public boolean existsByNomeRicettaAndCuoco(String nomeRicetta, Cuoco cuoco) {
		return this.ricettaRepository.existsByNomeRicettaAndCuoco(nomeRicetta, cuoco);
	}


	public boolean existsByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.existsByNomeRicetta(nomeRicetta);
	}

	
	
	
	/*===============================================================================================*/
	/*                                          FIND METHODS                                         */
	/*===============================================================================================*/
	
	
	
	
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
	
	
	public Ricetta findByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.findByNomeRicetta(nomeRicetta);
	}

	
	public Iterable<Ricetta> findAllByNomeRicetta(String nomeRicetta) {
		return this.ricettaRepository.findAllByNomeRicetta(nomeRicetta);
	}

	
	public List<Ricetta> findAllByIngrediente(Ingrediente ingredienteInfos) {
		List<Long> ricetteIDs = this.ricettaRepository.findAllRicettaIDByIngredienteID(ingredienteInfos.getId());
		List<Ricetta> ricette = new ArrayList<>();
		
		for(Long id : ricetteIDs) {
			ricette.add(this.findById(id));
		}
		
		return ricette;
	}


	public Iterable<Ricetta> findAllByCuocoOrderByNomeRicettaAsc(Cuoco cuoco) {
		return this.ricettaRepository.findAllByCuocoOrderByNomeRicettaAsc(cuoco);
	}
}
