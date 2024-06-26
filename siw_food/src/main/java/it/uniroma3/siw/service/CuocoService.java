package it.uniroma3.siw.service;

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

}