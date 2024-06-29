package it.uniroma3.siw.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long>{
	
	public boolean existsByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita);

	public Cuoco findByNomeAndCognomeAndDataNascita(String nome, String cognome, LocalDate dataNascita);

	public Iterable<Cuoco> findAllByOrderByNomeAsc();
	
}
