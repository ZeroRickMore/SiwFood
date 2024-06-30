package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validation.IngredienteValidator;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;
import jakarta.validation.Valid;

@Controller
public class IngredienteController {

	/*##############################################################*/
	/*##########################SERVICES############################*/
	/*##############################################################*/
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
//======================================================================\\

	/*##############################################################*/
	/*########################/SHOW METHODS#########################*/
	/*##############################################################*/

	@GetMapping("/elencoIngredienti")
	public String showElencoIngredienti(Model model) {
		Iterable<Ingrediente> allIngredienti = this.ingredienteService.findAll();
		model.addAttribute("allIngredienti", allIngredienti);
		return "elencoIngredienti.html";
	}
	
	@GetMapping("/ingrediente/{id}")
	public String showIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingrediente.html";
	}

	/*##############################################################*/
	/*######################/INSERT METHODS#########################*/
	/*##############################################################*/

	@GetMapping("/aggiungiIngrediente")
	public String showFormAggiungiIngrediente(Model model) {
		model.addAttribute("nuovoIngrediente", new Ingrediente());
		return "formAggiungiIngrediente.html";
	}

	@PostMapping("/aggiungiIngrediente")
	public String newIngrediente(@Valid @ModelAttribute("nuovoIngrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);

		if(bindingResult.hasErrors()) {
			//Print del th:href con il link al duplicato, qualora l'errore fosse quello
			Ingrediente ingredienteInDB = this.ingredienteService.findByNome(ingrediente.getNome());
			
			if(ingredienteInDB!=null)
				model.addAttribute("vecchioIngrediente", ingredienteInDB);

			return "formAggiungiIngrediente.html";
		}

		else {
			this.ingredienteService.save(ingrediente);
			return "redirect:ingrediente/"+ingrediente.getId();
		}
	}

	/*##############################################################*/
	/*######################/REMOVE METHODS#########################*/
	/*##############################################################*/
	
	@GetMapping("/rimuoviIngrediente")
	public String showFormRimuoviIngrediente(Model model) {
		model.addAttribute("ingredienteDaRimuovere", new Ingrediente());
		return "formRimuoviIngrediente.html";
	}

	@PostMapping("/rimuoviIngrediente")
	public String rimuoviIngrediente(@Valid @ModelAttribute("ingredienteDaRimuovere") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(bindingResult.hasErrors()) { //Significa che l'ingrediente esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("ingrediente.duplicato")) {
				
				this.ingredienteService.delete(ingrediente);
				return "redirect:elencoIngredienti"; //Unico caso funzionante!
			}
			return "formRimuoviIngrediente.html"; //Ho problemi ma non ingrediente.duplicato, quindi lo user ha toppato
		}

		bindingResult.reject("ingrediente.nonEsiste");
		return "formRimuoviIngrediente.html"; //Ha inserito un ingrediente che non esiste
		
	}
	
}
