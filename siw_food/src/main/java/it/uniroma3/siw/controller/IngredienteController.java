package it.uniroma3.siw.controller;

import java.util.List;

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
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.IngredienteService;
import jakarta.validation.Valid;

@Controller
public class IngredienteController {

	
	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	
	
	
//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
//=======================================================================================================\\

	
	
	
	/*===============================================================================================*/
	/*                                          SHOW METHODS                                         */
	/*===============================================================================================*/

	
	
	//Per tutti
	@GetMapping("/elencoIngredienti")
	public String showElencoIngredienti(Model model) {
		Iterable<Ingrediente> allIngredienti = this.ingredienteService.findAll();
		model.addAttribute("allIngredienti", allIngredienti);
		return "elencoIngredienti.html";
	}
	
	//Per tutti
	@GetMapping("/ingrediente/{id}")
	public String showIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingrediente.html";
	}

	
	
	
	/*===============================================================================================*/
	/*                                         INSERT METHODS                                        */
	/*===============================================================================================*/

	
	
	//Per admin e cuoco (identico)
	@GetMapping("/aggiungiIngrediente")
	public String showFormAggiungiIngrediente(Model model) {
		model.addAttribute("nuovoIngrediente", new Ingrediente());
		model.addAttribute("elencoUnitàDiMisura", Ingrediente.getUnitàdimisurapossibili());
		return "formAggiungiIngrediente.html";
	}

	
	//Per admin e cuoco (identico)
	@PostMapping("/aggiungiIngrediente")
	public String newIngrediente(@Valid @ModelAttribute("nuovoIngrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);

		if(bindingResult.hasErrors()) {
			//Print del th:href con il link al duplicato, qualora l'errore fosse quello
			Ingrediente ingredienteInDB = this.ingredienteService.findByNome(ingrediente.getNome());
			
			if(ingredienteInDB!=null)
				model.addAttribute("vecchioIngrediente", ingredienteInDB);
			System.out.println(bindingResult.getAllErrors().toString());
			model.addAttribute("elencoUnitàDiMisura", Ingrediente.getUnitàdimisurapossibili());
			return "formAggiungiIngrediente.html";
		}

		else {
			this.ingredienteService.save(ingrediente);
			return "redirect:ingrediente/"+ingrediente.getId();
		}
	}

	
	
	
	/*===============================================================================================*/
	/*                                         REMOVE METHODS                                        */
	/*===============================================================================================*/
	
	
	//INUTILIZZATO PER MIGLIORE SOLUZIONE, MA MI DISPIACEVA TOGLIERLO
	//Per admin -> Cuoco non può cancellare i propri ingrediente da specifiche
	@GetMapping("/admin/rimuoviIngredienteByPost")
	public String showFormRimuoviIngredienteByPost(Model model) {
		model.addAttribute("ingredienteDaRimuovere", new Ingrediente());
		model.addAttribute("elencoUnitàDiMisura", Ingrediente.getUnitàdimisurapossibili());
		return "/admin/formRimuoviIngredienteByPost.html";
	}
	
	//Per admin
	@GetMapping("/admin/rimuoviIngrediente")
	public String showElencoRimuoviIngrediente(Model model) {
		Iterable<Ingrediente> allIngredienti = this.ingredienteService.findAll();
		model.addAttribute("allIngredienti", allIngredienti);
		return "/admin/elencoRimuoviIngrediente.html";
	}
	
	//Per admin -> Cuoco non può cancellare i propri ingrediente da specifiche
	@GetMapping("/admin/rimuoviIngrediente/{id}")
	public String rimuoviIngredienteById(@ModelAttribute("id") Long idIngrediente, Model model) {
		
		Ingrediente toRemove = this.ingredienteService.findById(idIngrediente);
		
		if(toRemove == null)
			return "redirect:/ingrediente/-1"; //Non modello errori per chi gioca con gli URL
		
		this.ingredienteService.delete(toRemove);
		
		return "redirect:/elencoIngredienti";
	}

	//INUTILIZZATO PER MIGLIORE SOLUZIONE, MA MI DISPIACEVA TOGLIERLO
	//Per admin -> Cuoco non può cancellare i propri ingrediente da specifiche
	@PostMapping("/admin/rimuoviIngrediente")
	public String rimuoviIngrediente(@Valid @ModelAttribute("ingredienteDaRimuovere") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(bindingResult.hasErrors()) { //Significa che l'ingrediente esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("ingrediente.duplicato")) {
				
				this.ingredienteService.delete(ingrediente);
				return "redirect:/elencoIngredienti"; //Unico caso funzionante!
			}

			model.addAttribute("elencoUnitàDiMisura", Ingrediente.getUnitàdimisurapossibili());
			return "/admin/formRimuoviIngredienteByPost.html"; //Ho problemi ma non ingrediente.duplicato, quindi lo user ha toppato
		}
		
		
		model.addAttribute("elencoUnitàDiMisura", Ingrediente.getUnitàdimisurapossibili());
		bindingResult.reject("ingrediente.nonEsiste");
		return "/admin/formRimuoviIngredienteByPost.html"; //Ha inserito un ingrediente che non esiste
		
	}
	
	
	
	
	/*===============================================================================================*/
	/*                                            RICERCA                                            */
	/*===============================================================================================*/
	//Tutto per tutti
	
	
	@GetMapping("/formRicercaIngrediente")
	public String showFormRicercaIngrediente(Model model) {
		model.addAttribute("ingredienteInfos", new Ingrediente());
		return "formRicercaIngrediente.html";
	}
	

	@PostMapping("/ricercaPerNomeIngrediente")
	public String showIngredienteConStessoNome(@Valid @ModelAttribute("ingredienteInfos") Ingrediente ingrediente,
					BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasFieldErrors("nomeIngrediente")) {
			return "redirect:/ingrediente/-1"; //Un modo carino per sfruttare il template della ingrediente per una ingrediente che non esiste
		}
		
		ingrediente = this.ingredienteService.findByNome(ingrediente.getNome());
		
		if(ingrediente!=null) {
			return "redirect:ingrediente/"+ingrediente.getId();
		}
		
		return "redirect:/ingrediente/-1"; //Un modo carino per sfruttare il template della ingrediente per una ingrediente che non esiste
	}
	
	
	
	/*===============================================================================================*/
	/*                                        SUPPORT METHODS                                        */
	/*===============================================================================================*/
	
	
	
	
}
