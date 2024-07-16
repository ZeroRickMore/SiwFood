package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {




	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/




	@Autowired
	public UserRepository userRepository;

	//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
	//=======================================================================================================\\




	/*===============================================================================================*/
	/*                                          SAVE METHODS                                         */
	/*===============================================================================================*/




	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	

	
	/*===============================================================================================*/
	/*                                           GET METHODS                                         */
	/*===============================================================================================*/


	
	
	public User getUser(Long id) {
		return this.userRepository.findById(id).get();
	}

	
	public void delete(User user) {
		this.userRepository.delete(user);
	}
	
	
	public User findByCuoco(Cuoco c) {
		try {
			return this.userRepository.findByCuoco(c);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	
	public void deleteCuocoAssociato(Cuoco associato) {
		User utenteCheHaQuelCuoco = this.findByCuoco(associato);
		
		if(utenteCheHaQuelCuoco != null)
			this.userRepository.deleteCuocoAssociato(utenteCheHaQuelCuoco.getId());
	}
}
