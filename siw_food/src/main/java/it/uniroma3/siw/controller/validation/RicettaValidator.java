package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.RicettaService;

@Component
public class RicettaValidator implements Validator {
	
	/*##############################################################*/
	/*#########################SERVICES#############################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaService ricettaService;
	
	/*##############################################################*/
	/*#########################VALIDATE#############################*/
	/*##############################################################*/
	

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}
	
	/*##############################################################*/
	/*##################VALIDATE SUPPORT METHODS####################*/
	/*##############################################################*/
	
	
	
	
	/*##############################################################*/
	/*###########################SUPPORTS###########################*/
	/*##############################################################*/
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ricetta.class.equals(clazz);
	}
	
}
