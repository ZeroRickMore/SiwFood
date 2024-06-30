package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private IngredienteService ingredienteService;
	



	/*===============================================================================================*/
	/*                                            VALIDATE                                           */
	/*===============================================================================================*/
	
	
	
	
	@Override
	public void validate(Object o, Errors errors) {
		Ingrediente ingrediente = (Ingrediente) o;
		//Duplicità
		if(ingrediente.getNome()!=null
				&& this.ingredienteService.existsByNome(ingrediente.getNome())) {
			errors.reject("ingrediente.duplicato");
		}
	}


	
	
	/*===============================================================================================*/
	/*                                    VALIDATE SUPPORT METHOD                                    */
	/*===============================================================================================*/



	
	/*===============================================================================================*/
	/*                                            SUPPORTS                                           */
	/*===============================================================================================*/
	
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

}
