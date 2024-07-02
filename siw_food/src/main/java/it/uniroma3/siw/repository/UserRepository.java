package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.User;
import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long>{

	
	@Modifying
    @Transactional
    @Query(value = "UPDATE users SET cuoco_id = NULL", nativeQuery = true)
	void deleteCuocoAssociato(Long id);
	 

}
