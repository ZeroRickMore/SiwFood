package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validation.RicettaValidator;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {

	@Autowired
	private RicettaService ricettaService;
	
	/*##############################################################*/
	/*#########################VALIDATOR############################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaValidator ricettaValidator;
	
}
