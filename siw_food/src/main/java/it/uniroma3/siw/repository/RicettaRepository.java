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

	@Query(value = "INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE id=:idIngrediente), (SELECT id FROM ricetta WHERE id=:idRicetta))", nativeQuery = true)
	public void insertRicettaIngredienteIntoRicettaIngrediente2Quantità(@Param("idIngrediente") Long idIngrediente, @Param("idRicetta") Long idRicetta);
	
	
	@Query(value = "DELETE FROM ricetta_ingrediente2quantità WHERE ingrediente2quantity_key = :idIngrediente AND ricetta_id = :idRicetta", nativeQuery = true)
	public void deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(@Param("idIngrediente") Long idIngrediente, @Param("idRicetta") Long idRicetta);
	
}