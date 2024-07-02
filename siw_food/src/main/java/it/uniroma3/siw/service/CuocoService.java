package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.repository.CuocoRepository;

@Service
public class CuocoService {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private CuocoRepository cuocoRepository;
	
	
	

//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
//=======================================================================================================\\

			

			
	/*===============================================================================================*/
	/*                                       SAVE DELETE METHODS                                     */
	/*===============================================================================================*/




	public Cuoco save(Cuoco cuoco) {
		return this.cuocoRepository.save(cuoco);	
	}

	@Transactional(isolation=Isolation.SERIALIZABLE) 
	//Visto che sto cancellando un Cuoco, non posso lasciare che vengano modificati valori nel mentre.
	//Controllo NullPointer non necessario, visto che entro qui dentro solo se prima ho trovato che il cuoco esisteva
	public void delete(Cuoco cuoco) {
		Cuoco del = this.cuocoRepository.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
		this.cuocoRepository.delete(del);
	}

	
	
	
	/*===============================================================================================*/
	/*                                         EXISTS METHODS                                        */
	/*===============================================================================================*/


	
	
	public boolean existsByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita) {
		return this.cuocoRepository.existsByNomeAndCognomeAndDataNascita(nome, cognome, dataNascita);
	}

	

	
	/*===============================================================================================*/
	/*                                          FIND METHODS                                         */
	/*===============================================================================================*/


	
	
	public Iterable<Cuoco> findAll() {
		return this.cuocoRepository.findAll();
	}

	
	
	public Cuoco findById(Long id) {
		try {
			return this.cuocoRepository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}


	
	public Cuoco findByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita) {
		try {
			return this.cuocoRepository.findByNomeAndCognomeAndDataNascita(nome, cognome, dataNascita);
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}



	public Iterable<Cuoco> findAllByOrderByNomeAsc() {
		return this.cuocoRepository.findAllByOrderByNomeAsc();
	}

	public Iterable<Cuoco> findAllByNome(String nome) {
		return this.cuocoRepository.findAllByNomeOrderByCognomeAsc(nome);
	}

	public Iterable<Cuoco> findAllByCognome(String cognome) {
		return this.cuocoRepository.findAllByCognomeOrderByNomeAsc(cognome);
	}

}