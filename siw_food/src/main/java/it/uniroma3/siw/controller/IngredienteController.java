package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;

@Controller
public class IngredienteController {

	/*##############################################################*/
	/*##########################SERVICES############################*/
	/*##############################################################*/
	
	@Autowired
	private IngredienteService ingredienteService;
	
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
//======================================================================\\

	/*##############################################################*/
	/*#########################/ingredienti#########################*/
	/*##############################################################*/

	@GetMapping("/elencoIngredienti")
	public String showElencoIngredienti(Model model) {
		Iterable<Ingrediente> allIngredienti = this.ingredienteService.findAll();
		model.addAttribute("allIngredienti", allIngredienti);
		return "elencoIngredienti.html";
	}

	@GetMapping("/ingrediente/{id}")
	public String showCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingrediente.html";
	}
	
}
