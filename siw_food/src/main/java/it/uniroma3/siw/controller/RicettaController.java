package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validation.RicettaValidator;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.IngredienteService;
import it.uniroma3.siw.service.RicettaService;
import jakarta.validation.Valid;

@Controller
public class RicettaController {

	/*##############################################################*/
	/*##########################SERVICES############################*/
	/*##############################################################*/

	@Autowired
	private RicettaService ricettaService;

	@Autowired
	private CuocoService cuocoService;
	
	@Autowired
	private IngredienteService ingredienteService;

	/*##############################################################*/
	/*#########################VALIDATOR############################*/
	/*##############################################################*/

	@Autowired
	private RicettaValidator ricettaValidator;

	


	//======================================================================\\
	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
	//======================================================================\\

	/*##############################################################*/
	/*########################/SHOW METHODS#########################*/
	/*##############################################################*/

	@GetMapping("/elencoRicette")
	public String showelencoRicette(Model model) {
		Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
		model.addAttribute("allRicette", allRicette);
		return "elencoRicette.html";
	}

	@GetMapping("/ricetta/{id}")
	public String showRicetta(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.findById(id);
		ricetta.setTuttiPathDelleImmaginiFromOwnString();
		model.addAttribute("ricetta", ricetta);
		model.addAttribute("listaRicette", ricetta.getIngrediente2quantity().keySet());
		//Poteva essere fatto anche sul template, ma qui è più "pulito" a mio avviso

		return "ricetta.html";
	}
	
	/*##############################################################*/
	/*#################MODIFICA INGREDIENTI RICETTA#################*/
	/*##############################################################*/
	
	@GetMapping("/modificaIngredientiRicetta")
	public String showelencoRicettePerModificareIngredienti(Model model) {
		Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
		model.addAttribute("allRicette", allRicette);
		return "elencoPerSelezionareRicettaPerIngredienti.html";
	}
	
	@GetMapping("/modificaIngredientiRicetta/{ricettaId}")
	public String showModificaIngredientiRicetta(@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		
		if(ricetta==null) {
			return "elencoPerSelezionareRicettaPerIngredienti.html"; //Non metto errori, non modello per persone che giocano con gli url...
		}
		
		List<Ingrediente> allIngredientiMessi = new ArrayList<>(ricetta.getIngrediente2quantity().keySet()); //La lista degli ingredienti presenti nella ricetta
		
		List<Ingrediente> allIngredientiDisponibili = (List<Ingrediente>) this.ingredienteService.findAll();
		
		allIngredientiDisponibili.removeAll(allIngredientiMessi);
		
		model.addAttribute("allIngredientiMessi", allIngredientiMessi);
		model.addAttribute("allIngredientiDisponibili", allIngredientiDisponibili);
		model.addAttribute("ricetta", ricetta);
		
		return "modificaIngredientiRicetta.html";
	}
	
	
	//AGGUNGI INGREDIENTE ALLA RICETTA
	@PostMapping("/addIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndAddIngrediente(@PathVariable("ricettaId") Long ricettaId, 
			@PathVariable("ingredienteId") Long ingredienteId, @RequestParam("ingredienteQuantity") Integer ingredienteQuantity, Model model) {

		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);
		
		if(ricetta==null || ingrediente==null) {
			return "redirect:../../../modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url...
		}
		
		this.ricettaService.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteQuantity, ingredienteId, ricettaId);

		return "redirect:/modificaIngredientiRicetta/"+ricettaId;
		
	}
	
	@GetMapping("/removeIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndRemoveIngrediente(@PathVariable("ricettaId") Long ricettaId, @PathVariable("ingredienteId") Long ingredienteId, Model model) {

		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);
		
		if(ricetta==null || !ricetta.getIngrediente2quantity().containsKey(ingrediente)) {
			return "redirect:../../../modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url...
		}
		
		this.ricettaService.deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId);
		
		return "redirect:/modificaIngredientiRicetta/"+ricettaId;
	}

	/*##############################################################*/
	/*######################/INSERT METHODS#########################*/
	/*##############################################################*/

	@GetMapping("/aggiungiRicettaCompleta")
	public String showFormAggiungiRicettaCompleta(Model model) {
		model.addAttribute("nuovaRicetta", new Ricetta());
		model.addAttribute("cuoco", new Cuoco());
		this.addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(model);
		return "formAggiungiRicettaCompleta.html";
	}

	@PostMapping("/aggiungiRicettaCompleta")
	public String newRicettaCompleta(@Valid @ModelAttribute("nuovaRicetta") Ricetta ricetta, BindingResult bindingResult,
			@ModelAttribute("cuoco") Cuoco cuoco, Model model) {

		this.parseIntoCuocoFields(cuoco);

		//Ricerca del cuoco relativo sulla base di nome, cognome e data di nascita, e assegnazione alla ricetta
		Cuoco cuocoRelativo =  this.cuocoService.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
		ricetta.setCuoco(cuocoRelativo);
		this.ricettaValidator.validate(ricetta, bindingResult);
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().toString());
			//Print del th:href con il link al duplicato, qualora l'errore fosse quello
			Ricetta ricettaInDB = this.ricettaService.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), ricetta.getCuoco());

			if(ricettaInDB!=null) 
				model.addAttribute("vecchiaRicetta", ricettaInDB);

			return "formAggiungiRicettaCompleta.html";
		}

		else {
			this.ricettaService.save(ricetta);
			return "redirect:ricetta/"+ricetta.getId();
		}
	}

	@GetMapping("/aggiungiRicetta")
	public String showFormAggiungiRicetta(Model model) {
		
		
		
		model.addAttribute("nuovaRicetta", new Ricetta());
		return "formAggiungiRicetta.html";
	}

	@PostMapping("/aggiungiRicetta")
	public String newRicetta(@Valid @ModelAttribute("nuovaRicetta") Ricetta ricetta, BindingResult bindingResult, Model model) {

		//Ricerca del cuoco relativo sulla base di nome, cognome e data di nascita, e assegnazione alla ricetta

		this.ricettaValidator.validateStessoNomeNoCuoco(ricetta, bindingResult);

		if(bindingResult.hasErrors()) {
			return "formAggiungiRicetta.html";
		}
		else {
			this.ricettaService.save(ricetta);
			return "redirect:ricetta/"+ricetta.getId();
		}
	}

	/*##############################################################*/
	/*######################/REMOVE METHODS#########################*/
	/*##############################################################*/

	@GetMapping("/rimuoviRicetta")
	public String showFormRimuoviRicetta(Model model) {
		model.addAttribute("ricettaDaRimuovere", new Ricetta());
		model.addAttribute("cuoco", new Cuoco());
		this.addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(model);

		return "formRimuoviRicetta.html";
	}

	@PostMapping("/rimuoviRicetta")
	public String rimuoviRicetta(@Valid @ModelAttribute("ricettaDaRimuovere") Ricetta ricetta, BindingResult bindingResult, 
			@ModelAttribute("cuoco") Cuoco cuoco, Model model) {
		
		//IF che rimuove la ricetta con un cuoco
		if(!cuoco.getNome().equals("Nessun cuoco")) {
			
			this.parseIntoCuocoFields(cuoco);
			
			cuoco = this.cuocoService.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
			ricetta.setCuoco(cuoco);
			
			this.ricettaValidator.validate(ricetta, bindingResult);
		} else { //Se non ho assegnato un cuoco
			ricetta.setCuoco(null);
			this.ricettaValidator.validateStessoNomeNoCuoco(ricetta, bindingResult);
		}
		
		//Controllo sugli errori e act accordingly
		
		if(bindingResult.hasErrors()) { //Significa che la variant esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("ricetta.duplicato")) { 
				this.ricettaService.delete(ricetta);
				return "redirect:elencoRicette"; //Caso funzionante se aveva messo un Cuoco
			}
			if(bindingResult.getAllErrors().toString().contains("ricetta.stessoNomeMaNoCuoco")) { 
				Ricetta ricettaConNomeUguale = this.ricettaService.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), null);
					this.ricettaService.delete(ricettaConNomeUguale);
				
				return "redirect:elencoRicette"; //Caso funzionante se non aveva messo cuochi
			}
			return "formRimuoviRicetta.html"; //Ho problemi ma non ricetta.duplicato, quindi lo user ha toppato
		}

		bindingResult.reject("ricetta.nonEsiste");
		return "formRimuoviRicetta.html"; //Ha inserito un ricetta che non esiste

	}

	/*##############################################################*/
	/*######################/SUPPORT METHODS########################*/
	/*##############################################################*/

	private void addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(Model model) {
		//============================================================
		//Add the editori attributes for menu a tendina
		Set<String> elencoNomeCognomeData = new TreeSet<>(); //No dups (impossible but yeah)

		for(Cuoco c : this.cuocoService.findAllByOrderByNomeAsc()) {
			elencoNomeCognomeData.add(c.getNome()+ " - " + c.getCognome() + " - " + c.getDataNascita().toString());
		}

		model.addAttribute("elencoNomeCognomeData", elencoNomeCognomeData);
		//============================================================
	}

	private void parseIntoCuocoFields(Cuoco cuoco) {
		try { 
			String[] campiCuoco = cuoco.getNome().split(" - ");

			cuoco.setNome(campiCuoco[0]);
			cuoco.setCognome(campiCuoco[1]);
			String dataNascitaStringa = campiCuoco[2];

			cuoco.setDataNascita(LocalDate.parse(dataNascitaStringa, DateTimeFormatter.ISO_LOCAL_DATE));
		} catch (Exception e) {}
	}
}
