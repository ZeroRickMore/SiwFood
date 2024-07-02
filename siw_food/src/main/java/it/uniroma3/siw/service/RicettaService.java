package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void insertRicettaIngredienteIntoRicettaIngrediente2Quantità(Integer quantity, Long ingredienteId, Long ricettaId) {
		this.ricettaRepository.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId, quantity);
		
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	//Ci sono molte operazioni nel db, meglio renderlo atomico
	public void delete(Ricetta ricetta) {
		Ricetta del = this.ricettaRepository.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), ricetta.getCuoco());
		this.ricettaRepository.delete(del);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	//Ci sono molte operazioni nel db, meglio renderlo atomico
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

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	//Tante letture, meglio renderlo un po' più lockato
	//Si può migliorare con una query joinata
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
