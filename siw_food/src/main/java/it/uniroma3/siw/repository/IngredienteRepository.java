package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingrediente;
import jakarta.transaction.Transactional;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{
	
	public boolean existsByNome(String nome);
	
	public Ingrediente findByNome(String nome);
	
	@Transactional //Probabilmente non serve grazie a quella del service
	@Modifying
	@Query(value = "DELETE FROM ricetta_ingrediente2quantità WHERE ingrediente2quantity_key = :idIngrediente", nativeQuery = true)
	public void deleteRowsWithIngredienteFromRicettaIngrediente2Quantità(@Param("idIngrediente") Long id);

	public List<Ingrediente> findAllByOrderByNome();
	
}