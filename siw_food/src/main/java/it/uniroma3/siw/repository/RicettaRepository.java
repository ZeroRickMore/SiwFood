package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta, Long>{

	public Ricetta findByNomeRicettaAndCuoco(String nomeRicetta, Cuoco cuoco);

	public boolean existsByNomeRicettaAndCuoco(String nomeRicetta, Cuoco cuoco);
	
	public List<Ricetta> findAllByOrderByNomeRicettaAsc();

	public boolean existsByNomeRicetta(String nomeRicetta);

	public Ricetta findByNomeRicetta(String nomeRicetta);

	public Iterable<Ricetta> findAllByNomeRicetta(String nomeRicetta);

}
