package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.service.CuocoService;

@Component
public class CuocoValidator implements Validator {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private CuocoService cuocoService;
	



	/*===============================================================================================*/
	/*                                            VALIDATE                                           */
	/*===============================================================================================*/
	
	
	
	
	@Override
	public void validate(Object o, Errors errors) {
		Cuoco cuoco = (Cuoco) o;
		//Duplicità
		if(cuoco.getNome()!=null && cuoco.getCognome()!=null && cuoco.getDataNascita()!=null 
				&& this.cuocoService.existsByNomeAndCognomeAndDataNascita(cuoco.getNome(), 
						cuoco.getCognome(), cuoco.getDataNascita())) {
			errors.reject("cuoco.duplicato"); 
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
		return Cuoco.class.equals(clazz);
	}
	
}
