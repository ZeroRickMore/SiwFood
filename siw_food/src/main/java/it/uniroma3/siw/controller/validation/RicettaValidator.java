package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.RicettaService;

@Component
public class RicettaValidator implements Validator {
	
	/*##############################################################*/
	/*#########################SERVICES#############################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaService ricettaService;
	
	@Autowired
	private CuocoService cuocoService;
	
	/*##############################################################*/
	/*#########################VALIDATE#############################*/
	/*##############################################################*/
	

	@Override
	public void validate(Object o, Errors errors) {
		Ricetta ricetta = (Ricetta) o;
		//Controllo duplicati
		Cuoco cuoco = ricetta.getCuoco();
		if(ricetta.getNomeRicetta()!=null && cuoco!=null &&
				this.ricettaService.existsByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), cuoco)) {
			errors.reject("ricetta.duplicato");
		}
		if(cuoco==null) {
			errors.reject("cuoco.notFound");
		}
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
