package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.repository.CuocoRepository;

@Service
public class CuocoService {
	
	/*##############################################################*/
	/*#########################REPOSITORY###########################*/
	/*##############################################################*/
	
	@Autowired
	private CuocoRepository cuocoRepository;


	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
	
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

	public Cuoco save(Cuoco cuoco) {
		return this.cuocoRepository.save(cuoco);	
	}

	public boolean existsByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita) {
		return this.cuocoRepository.existsByNomeAndCognomeAndDataNascita(nome, cognome, dataNascita);
	}
	
	public Cuoco findByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita) {
		try {
			return this.cuocoRepository.findByNomeAndCognomeAndDataNascita(nome, cognome, dataNascita);
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public void delete(Cuoco cuoco) {
		Cuoco del = this.cuocoRepository.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
		this.cuocoRepository.delete(del);
	}

	public Iterable<Cuoco> findAllByOrderByNomeAsc() {
		return this.cuocoRepository.findAllByOrderByNomeAsc();
	}

}