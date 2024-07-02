package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validation.CuocoValidator;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.RicettaService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class CuocoController {



	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/



	@Autowired
	private CuocoService cuocoService;

	@Autowired
	private RicettaService ricettaService;

	@Autowired
	private CuocoValidator cuocoValidator;

	@Autowired
	private AuthenticationController authenticationController; //Necessario per ottenere il cuoco corrente
	
	@Autowired
	private UserService userService;


	//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
	//=======================================================================================================\\




	/*===============================================================================================*/
	/*                                          SHOW METHODS                                         */
	/*===============================================================================================*/



	//Per tutti
	@GetMapping("/elencoCuochi")
	public String showElencoCuochi(Model model) {
		Iterable<Cuoco> allCuochi = this.cuocoService.findAll();
		model.addAttribute("allCuochi", allCuochi);
		return "elencoCuochi.html";
	}

	//Per tutti
	@GetMapping("/cuoco/{id}")
	public String showCuoco(@PathVariable("id") Long id, Model model) {
		Cuoco cuoco = this.cuocoService.findById(id);
		model.addAttribute("cuoco", cuoco);
		return "cuoco.html";
	}

	
	//Per cuoco
	@GetMapping("/showSelf")
	public String showSelfCuoco(Model model) {
		Cuoco cuocoCorrente = this.authenticationController.getCuocoSessioneCorrente();
		
		if(cuocoCorrente==null) 
			return "redirect:/admin/index.html"; //Qui ci possono accedere admin o cuoco, se non è cuoco, è admin che ha fatto qualche test...
		
		return "redirect:/cuoco/"+cuocoCorrente.getId();
		
	}


	/*===============================================================================================*/
	/*                                         INSERT METHODS                                        */
	/*===============================================================================================*/



	//Per admin
	@GetMapping("/admin/aggiungiCuoco")
	public String showFormAggiungiCuoco(Model model) {
		model.addAttribute("nuovoCuoco", new Cuoco());
		return "/admin/formAggiungiCuoco.html";
	}

	//Per admin
	@PostMapping("/admin/aggiungiCuoco")
	public String newCuoco(@Valid @ModelAttribute("nuovoCuoco") Cuoco cuoco, BindingResult bindingResult, Model model) {
		this.cuocoValidator.validate(cuoco, bindingResult);

		if(bindingResult.hasErrors()) {
			//Print del th:href con il link al duplicato, qualora l'errore fosse quello
			Cuoco cuocoInDB = this.cuocoService.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());

			if(cuocoInDB!=null)
				model.addAttribute("vecchioCuoco", cuocoInDB);

			return "/admin/formAggiungiCuoco.html";
		}

		else {
			this.cuocoService.save(cuoco);
			return "redirect:/cuoco/"+cuoco.getId();
		}
	}





	/*===============================================================================================*/
	/*                                         REMOVE METHODS                                        */
	/*===============================================================================================*/




	//Per admin
	@GetMapping("/admin/rimuoviCuoco")
	public String showFormRimuoviCuoco(Model model) {
		model.addAttribute("cuocoDaRimuovere", new Cuoco());
		return "/admin/formRimuoviCuoco.html";
	}

	//Per admin
	@PostMapping("/admin/rimuoviCuoco")
	public String rimuoviCuoco(@Valid @ModelAttribute("cuocoDaRimuovere") Cuoco cuoco, BindingResult bindingResult, Model model) {
		this.cuocoValidator.validate(cuoco, bindingResult);

		if(bindingResult.hasErrors()) { //Significa che cuoco esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("cuoco.duplicato")) { 
				Cuoco toDelete = this.cuocoService.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
				this.userService.deleteCuocoAssociato(toDelete);
				this.cuocoService.delete(toDelete);
				return "redirect:/elencoCuochi"; //Unico caso funzionante!
			}
			return "/admin/formRimuoviCuoco.html"; //Ho problemi ma non cuoco.duplicato, quindi lo user ha toppato
		}

		bindingResult.reject("cuoco.nonEsiste");
		return "/admin/formRimuoviCuoco.html"; //Ha inserito un cuoco che non esiste

	}




	/*===============================================================================================*/
	/*                                     MODIFICA RICETTE CUOCO                                    */
	/*===============================================================================================*/


	

	//Per admin
	@GetMapping("/admin/modificaRicetteCuoco")
	public String showelencoRicettePerModificareIngredienti(Model model) {
		Iterable<Cuoco> allCuochi = this.cuocoService.findAllByOrderByNomeAsc();
		model.addAttribute("allCuochi", allCuochi);
		return "/admin/elencoPerSelezionareCuocoPerModificaRicette.html";
	}

	//Per admin
	@GetMapping("/admin/modificaRicetteCuoco/{cuocoId}")
	public String showModificaIngredientiCuoco(@PathVariable("cuocoId") Long cuocoId, Model model) {
		Cuoco cuoco = this.cuocoService.findById(cuocoId);

		if(cuoco==null) {
			Iterable<Cuoco> allCuochi = this.cuocoService.findAllByOrderByNomeAsc();
			model.addAttribute("allCuochi", allCuochi);
			return "/admin/elencoPerSelezionareCuocoPerModificaRicette.html"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		List<Ricetta> allRicetteMesse = new ArrayList<>(cuoco.getRicette()); //La lista degli ingredienti presenti nella cuoco

		List<Ricetta> allRicetteDisponibili = (List<Ricetta>) this.ricettaService.findAll();

		allRicetteDisponibili.removeAll(allRicetteMesse);

		model.addAttribute("allRicetteMesse", allRicetteMesse);
		model.addAttribute("allRicetteDisponibili", allRicetteDisponibili);
		model.addAttribute("cuoco", cuoco);

		return "/admin/modificaRicetteCuoco.html";
	}


	//-------------------------------------Aggiungi Ricetta a Cuoco-------------------------------------\\

	//Per admin
	@GetMapping("/admin/addRicetta/{cuocoId}/{ricettaId}")
	public String showModificaRicetteCuocoAndAddRicetta(@PathVariable("cuocoId") Long cuocoId, @PathVariable("ricettaId") Long ricettaId, Model model) {

		//Logica per aggiungere ingrediente a cuoco
		Cuoco cuoco = this.cuocoService.findById(cuocoId);
		Ricetta ricetta = this.ricettaService.findById(ricettaId);

		if(cuoco==null || ricetta==null) {
			return "redirect:/admin/modificaRicetteCuoco"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		cuoco.getRicette().add(ricetta);
		ricetta.setCuoco(cuoco);

		this.cuocoService.save(cuoco);
		this.ricettaService.save(ricetta);

		return "redirect:/admin/modificaRicetteCuoco/"+cuocoId;

	}

	//-------------------------------------Rimuovi Ricetta da Cuoco-------------------------------------\\

	//Per admin
	@GetMapping("/admin/removeRicetta/{cuocoId}/{ricettaId}")
	public String showModificaRicetteCuocoAndRemoveRicetta(@PathVariable("cuocoId") Long cuocoId, @PathVariable("ricettaId") Long ricettaId, Model model) {

		//Logica per aggiungere ingrediente a cuoco
		Cuoco cuoco = this.cuocoService.findById(cuocoId);
		Ricetta ricetta = this.ricettaService.findById(ricettaId);

		if(cuoco==null || !cuoco.getRicette().contains(ricetta)) {
			return "redirect:/admin/modificaRicetteCuoco"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		cuoco.getRicette().remove(ricetta);
		ricetta.setCuoco(null);

		this.cuocoService.save(cuoco);
		this.ricettaService.save(ricetta);

		return "redirect:/admin/modificaRicetteCuoco/"+cuocoId;
	}
	/*===============================================================================================*/
	/*                                            RICERCA                                            */
	/*===============================================================================================*/
	//Tutto per tutti


	@GetMapping("/formRicercaCuoco")
	public String showFormRicercaRicetta(Model model) {
		model.addAttribute("cuocoInfos", new Cuoco());
		return "formRicercaCuoco.html";
	}


	@PostMapping("/ricercaPerNomeCuoco")
	public String showCuochiConStessoNome(@Valid @ModelAttribute("cuocoInfos") Cuoco cuocoInfos,
			BindingResult bindingResult, Model model) {

		Iterable<Cuoco> allCuochi = this.cuocoService.findAllByNome(cuocoInfos.getNome()); //Non univoco

		model.addAttribute("allCuochi", allCuochi);
		return "elencoCuochi.html"; //Un modo carino per sfruttare il template dell' cuoco per un cuoco che non esiste
	}

	@PostMapping("/ricercaPerCognomeCuoco")
	public String showCuochiConStessoCognome(@Valid @ModelAttribute("cuocoInfos") Cuoco cuocoInfos,
			BindingResult bindingResult, Model model) {
		System.out.println(cuocoInfos.toString());
		
		Iterable<Cuoco> allCuochi = this.cuocoService.findAllByCognome(cuocoInfos.getCognome()); //Non univoco

		model.addAttribute("allCuochi", allCuochi);
		return "elencoCuochi.html"; //Un modo carino per sfruttare il template dell' cuoco per un cuoco che non esiste
	}




	/*===============================================================================================*/
	/*                                        SUPPORT METHODS                                        */
	/*===============================================================================================*/




}
