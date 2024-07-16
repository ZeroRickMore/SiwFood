package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	
	@Modifying
	@Transactional(isolation = Isolation.SERIALIZABLE) //Operazione molto delicata, lascerei il lock più alto
    @Query(value = "UPDATE users SET cuoco_id = NULL where id = :idUser", nativeQuery = true)
	void deleteCuocoAssociato(@Param("idUser") Long idUser);
	
	public User findByCuoco(Cuoco c);
	
}
