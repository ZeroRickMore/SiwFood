package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.controller.validation.RicettaValidator;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {

	/*##############################################################*/
	/*##########################SERVICES############################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaService ricettaService;
	
	/*##############################################################*/
	/*#########################VALIDATOR############################*/
	/*##############################################################*/
	
	@Autowired
	private RicettaValidator ricettaValidator;
	
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
//======================================================================\\

	/*##############################################################*/
	/*###########################/ricette###########################*/
	/*##############################################################*/

	@GetMapping("/elencoRicette")
	public String showelencoRicette(Model model) {
		Iterable<Ricetta> allRicette = this.ricettaService.findAll();
		model.addAttribute("allRicette", allRicette);
		return "elencoRicette.html";
	}

	@GetMapping("/ricetta/{id}")
	public String showRicetta(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.findById(id);
		model.addAttribute("ricetta", ricetta);
		
		model.addAttribute("listaRicette", ricetta.getIngrediente2quantity().keySet()); 
		//Poteva essere fatto anche sul template, ma qui è più "pulito" a mio avviso
		
		return "ricetta.html";
	}

}
