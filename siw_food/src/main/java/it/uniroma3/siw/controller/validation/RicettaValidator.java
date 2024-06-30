package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.RicettaService;


@Component
public class RicettaValidator implements Validator {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private RicettaService ricettaService;




	/*===============================================================================================*/
	/*                                            VALIDATE                                           */
	/*===============================================================================================*/
	
	
	
	
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
			errors.reject("cuoco.nonEsiste");
		}
	}

	public void validateStessoNomeNoCuoco(Object o, Errors errors) {
		Ricetta ricetta = (Ricetta) o;

		if(ricetta.getCuoco()!=null) return; //Not what this was meant for...

		if(ricetta.getNomeRicetta()!=null && this.ricettaService.existsByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), null)) {
			errors.reject("ricetta.stessoNomeMaNoCuoco"); //Non voglio ricette senza cuochi con stesso nome, altrimenti va bene
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
		return Ricetta.class.equals(clazz);
	}



}
