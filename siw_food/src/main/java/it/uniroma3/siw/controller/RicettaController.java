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
import org.springframework.web.bind.annotation.RequestMapping;
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




	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/




	@Autowired
	private RicettaService ricettaService;

	@Autowired
	private CuocoService cuocoService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private RicettaValidator ricettaValidator;




	//=======================================================================================================\\
	/*===============================================================================================*/
	/*                                            METHODS                                            */
	/*===============================================================================================*/
	//=======================================================================================================\\




	/*===============================================================================================*/
	/*                                          SHOW METHODS                                         */
	/*===============================================================================================*/



	//Per tutti
	@GetMapping("/elencoRicette")
	public String showelencoRicette(Model model) {
		Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
		model.addAttribute("allRicette", allRicette);
		return "elencoRicette.html";
	}

	//Per tutti
	@GetMapping("/ricetta/{id}")
	public String showRicetta(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.findById(id);

		if(ricetta!=null) {
			ricetta.setTuttiPathDelleImmaginiFromOwnString();
			//Poteva essere fatto anche sul template, ma qui è più "pulito" a mio avviso
			model.addAttribute("listaIngredienti", ricetta.getIngrediente2quantity().keySet());
		}

		model.addAttribute("ricetta", ricetta);

		return "ricetta.html";
	}




	/*===============================================================================================*/
	/*                                         INSERT METHODS                                        */
	/*===============================================================================================*/



	//Per admin -> Aggiunge ricetta con pure il cuoco. Admin va solo qui
	@GetMapping("/admin/aggiungiRicettaCompleta")
	public String showFormAggiungiRicettaCompleta(Model model) {
		model.addAttribute("nuovaRicetta", new Ricetta());
		model.addAttribute("cuoco", new Cuoco());
		this.addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(model);
		return "/admin/formAggiungiRicettaCompleta.html";
	}

	//Per admin -> Aggiunge ricetta con pure il cuoco. Admin va solo qui
	@PostMapping("/admin/aggiungiRicettaCompleta")
	public String newRicettaCompleta(@Valid @ModelAttribute("nuovaRicetta") Ricetta ricetta, BindingResult bindingResult,
			@ModelAttribute("cuoco") Cuoco cuoco, Model model) {

		this.parseIntoCuocoFields(cuoco);

		//Ricerca del cuoco relativo sulla base di nome, cognome e data di nascita, e assegnazione alla ricetta
		Cuoco cuocoRelativo =  this.cuocoService.findByNomeAndCognomeAndDataNascita(cuoco.getNome(), cuoco.getCognome(), cuoco.getDataNascita());
		ricetta.setCuoco(cuocoRelativo); //Se è null, lo verifica il validate!

		this.ricettaValidator.validate(ricetta, bindingResult);
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().toString());
			//Print del th:href con il link al duplicato, qualora l'errore fosse quello
			Ricetta ricettaInDB = this.ricettaService.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), ricetta.getCuoco());

			if(ricettaInDB!=null) 
				model.addAttribute("vecchiaRicetta", ricettaInDB);

			return "/admin/formAggiungiRicettaCompleta.html";
		}

		else {
			this.ricettaService.save(ricetta);
			return "redirect:/ricetta/"+ricetta.getId();
		}
	}

	//Per cuoco
	@GetMapping("/aggiungiRicetta")
	public String showFormAggiungiRicetta(Model model) {

		model.addAttribute("nuovaRicetta", new Ricetta());
		return "formAggiungiRicetta.html";
	}

	//Per cuoco
	@PostMapping("/aggiungiRicetta")
	public String newRicetta(@Valid @ModelAttribute("nuovaRicetta") Ricetta ricetta, BindingResult bindingResult, Model model) {

		//Ricerca del cuoco relativo sulla base di nome, cognome e data di nascita, e assegnazione alla ricetta

		this.ricettaValidator.validateStessoNomeNoCuoco(ricetta, bindingResult);

		if(bindingResult.hasErrors()) {
			return "formAggiungiRicetta.html";
		}
		else {
			this.ricettaService.save(ricetta);
			return "redirect:/ricetta/"+ricetta.getId();
		}
	}



	/*===============================================================================================*/
	/*                                         REMOVE METHODS                                        */
	/*===============================================================================================*/


	//Per admin
	@GetMapping("/admin/rimuoviRicetta")
	public String showFormRimuoviRicettaAdmin(Model model) {
		model.addAttribute("ricettaDaRimuovere", new Ricetta());
		model.addAttribute("cuoco", new Cuoco());
		this.addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(model);

		return "/admin/formRimuoviRicetta.html";
	}

	//Per admin
	@PostMapping("/admin/rimuoviRicetta")
	public String rimuoviRicettaAdmin(@Valid @ModelAttribute("ricettaDaRimuovere") Ricetta ricetta, BindingResult bindingResult, 
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

		if(bindingResult.hasErrors()) { //Significa che la ricetta esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("ricetta.duplicato")) { 
				this.ricettaService.delete(ricetta);
				return "redirect:/elencoRicette"; //Caso funzionante se aveva messo un Cuoco
			}
			if(bindingResult.getAllErrors().toString().contains("ricetta.stessoNomeMaNoCuoco")) { 
				Ricetta ricettaConNomeUguale = this.ricettaService.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), null);
				this.ricettaService.delete(ricettaConNomeUguale);

				return "redirect:/elencoRicette"; //Caso funzionante se non aveva messo cuochi
			}
			return "/admin/formRimuoviRicetta.html"; //Ho problemi ma non ricetta.duplicato, quindi lo user ha toppato
		}

		bindingResult.reject("ricetta.nonEsiste");
		return "/admin/formRimuoviRicetta.html"; //Ha inserito un ricetta che non esiste

	}




	//Per cuoco
	@GetMapping("/rimuoviRicetta")
	public String showFormRimuoviRicettaCuoco(Model model) {
		//LOGICA PER CUOCO
		model.addAttribute("ricettaDaRimuovere", new Ricetta());
		model.addAttribute("cuoco", new Cuoco());
		this.addStringListOfElencoNomiAndCognomiAndDataNascitaCuochiToModel(model);

		return "formRimuoviRicetta.html";
	}

	//Per cuoco
	@PostMapping("/rimuoviRicetta")
	public String rimuoviRicettaCuoco(@Valid @ModelAttribute("ricettaDaRimuovere") Ricetta ricetta, BindingResult bindingResult, 
			@ModelAttribute("cuoco") Cuoco cuoco, Model model) {
		//LOGICA PER CUOCO
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

		if(bindingResult.hasErrors()) { //Significa che la ricetta esiste oppure ci sono altri errori
			if(bindingResult.getAllErrors().toString().contains("ricetta.duplicato")) { 
				this.ricettaService.delete(ricetta);
				return "redirect:/elencoRicette"; //Caso funzionante se aveva messo un Cuoco
			}
			if(bindingResult.getAllErrors().toString().contains("ricetta.stessoNomeMaNoCuoco")) { 
				Ricetta ricettaConNomeUguale = this.ricettaService.findByNomeRicettaAndCuoco(ricetta.getNomeRicetta(), null);
				this.ricettaService.delete(ricettaConNomeUguale);

				return "redirect:/elencoRicette"; //Caso funzionante se non aveva messo cuochi
			}
			return "formRimuoviRicetta.html"; //Ho problemi ma non ricetta.duplicato, quindi lo user ha toppato
		}

		bindingResult.reject("ricetta.nonEsiste");
		return "formRimuoviRicetta.html"; //Ha inserito un ricetta che non esiste

	}




	/*===============================================================================================*/
	/*                               MODIFICA INGREDIENTI RICETTA ADMIN                              */
	/*===============================================================================================*/



	//Per admin
	@GetMapping("/admin/modificaIngredientiRicetta")
	public String showelencoRicettePerModificareIngredientiAdmin(Model model) {
		Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
		model.addAttribute("allRicette", allRicette);
		return "/admin/elencoPerSelezionareRicettaPerModificaIngredienti.html";
	}

	//Per admin
	@GetMapping("/admin/modificaIngredientiRicetta/{ricettaId}")
	public String showModificaIngredientiRicettaAdmin(@PathVariable("ricettaId") Long ricettaId, Model model) {

		Ricetta ricetta = this.ricettaService.findById(ricettaId);

		if(ricetta==null) {
			Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
			model.addAttribute("allRicette", allRicette);
			return "elencoPerSelezionareRicettaPerModificaIngredienti.html"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		this.setupModificaIngredientiRicetta(ricetta, model);

		return "/admin/modificaIngredientiRicetta.html";
	}



	
	//-------------------------------------Aggiungi Ingrediente a Ricetta-------------------------------------\\

	//Per admin
	@PostMapping("/admin/addIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndAddIngredienteAdmin(@PathVariable("ricettaId") Long ricettaId, 
			@PathVariable("ingredienteId") Long ingredienteId, @RequestParam("ingredienteQuantity") Integer ingredienteQuantity, Model model) {

		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);

		//Controllo per quantità strettamente positiva

		if(ingredienteQuantity <= 0) {
			this.setupModificaIngredientiRicetta(ricetta, model);
			model.addAttribute("qtyError", "");
			return "/admin/modificaIngredientiRicetta.html";
		}

		if(ricetta==null || ingrediente==null) {
			return "redirect:/admin/modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url e con la quantità
		}

		this.ricettaService.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteQuantity, ingredienteId, ricettaId);

		return "redirect:/admin/modificaIngredientiRicetta/"+ricettaId;

	}

	//-------------------------------------Rimuovi Ingrediente da Ricetta-------------------------------------\\	

	//Per admin
	@GetMapping("/admin/removeIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndRemoveIngredienteAdmin(@PathVariable("ricettaId") Long ricettaId, @PathVariable("ingredienteId") Long ingredienteId, Model model) {

		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);

		if(ricetta==null || !ricetta.getIngrediente2quantity().containsKey(ingrediente)) {
			return "redirect:/admin/modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		this.ricettaService.deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId);

		return "redirect:/admin/modificaIngredientiRicetta/"+ricettaId;
	}



	/*===============================================================================================*/
	/*                               MODIFICA INGREDIENTI RICETTA CUOCO                              */
	/*===============================================================================================*/



	//Per cuoco
	@GetMapping("/modificaIngredientiRicetta")
	public String showelencoRicettePerModificareIngredientiCuoco(Model model) {
		
		//LOGICA PER CUOCO
		
		Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
		model.addAttribute("allRicette", allRicette);
		return "elencoPerSelezionareRicettaPerModificaIngredienti.html";
	}

	//Per cuoco
	@GetMapping("/modificaIngredientiRicetta/{ricettaId}")
	public String showModificaIngredientiRicettaCuoco(@PathVariable("ricettaId") Long ricettaId, Model model) {

		//LOGICA PER CUOCO
		
		Ricetta ricetta = this.ricettaService.findById(ricettaId);

		if(ricetta==null) {
			Iterable<Ricetta> allRicette = this.ricettaService.findAllByOrderByNomeRicettaAsc();
			model.addAttribute("allRicette", allRicette);
			return "elencoPerSelezionareRicettaPerModificaIngredienti.html"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		this.setupModificaIngredientiRicetta(ricetta, model);

		return "modificaIngredientiRicetta.html";
	}



	
	//-------------------------------------Aggiungi Ingrediente a Ricetta-------------------------------------\\

	//Per cuoco
	@PostMapping("/addIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndAddIngredienteCuoco(@PathVariable("ricettaId") Long ricettaId, 
			@PathVariable("ingredienteId") Long ingredienteId, @RequestParam("ingredienteQuantity") Integer ingredienteQuantity, Model model) {

		//LOGICA PER CUOCO
		
		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);

		//Controllo per quantità strettamente positiva

		if(ingredienteQuantity <= 0) {
			this.setupModificaIngredientiRicetta(ricetta, model);
			model.addAttribute("qtyError", "");
			return "modificaIngredientiRicetta.html";
		}

		if(ricetta==null || ingrediente==null) {
			return "redirect:/modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url e con la quantità
		}

		this.ricettaService.insertRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteQuantity, ingredienteId, ricettaId);

		return "redirect:/modificaIngredientiRicetta/"+ricettaId;

	}

	//-------------------------------------Rimuovi Ingrediente da Ricetta-------------------------------------\\	

	//Per cuoco
	@GetMapping("/removeIngrediente/{ricettaId}/{ingredienteId}")
	public String showModificaIngredientiRicettaAndRemoveIngredienteCuoco(@PathVariable("ricettaId") Long ricettaId, @PathVariable("ingredienteId") Long ingredienteId, Model model) {

		//LOGICA PER CUOCO
		
		//Logica per aggiungere ingrediente a ricetta
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		Ingrediente ingrediente = this.ingredienteService.findById(ingredienteId);

		if(ricetta==null || !ricetta.getIngrediente2quantity().containsKey(ingrediente)) {
			return "redirect:../../../modificaIngredientiRicetta"; //Non metto errori, non modello per persone che giocano con gli url...
		}

		this.ricettaService.deleteRicettaIngredienteIntoRicettaIngrediente2Quantità(ingredienteId, ricettaId);

		return "redirect:/modificaIngredientiRicetta/"+ricettaId;
	}




	/*===============================================================================================*/
	/*                                            RICERCA                                            */
	/*===============================================================================================*/
	//Tutto per tutti



	@GetMapping("/formRicercaRicetta")
	public String showFormRicercaRicetta(Model model) {
		model.addAttribute("ricettaInfos", new Ricetta());
		model.addAttribute("ingredienteInfos", new Ingrediente());
		return "formRicercaRicetta.html";
	}


	@PostMapping("/ricercaPerNomeRicetta")
	public String showRicettaConStessoNome(@Valid @ModelAttribute("ricettaInfos") Ricetta ricetta,
			BindingResult bindingResult, Model model) {

		if(bindingResult.hasFieldErrors("nomeRicetta")) {
			return "redirect:/ricetta/-1"; //Un modo carino per sfruttare il template della ricetta per una ricetta che non esiste
		}

		ricetta = this.ricettaService.findByNomeRicetta(ricetta.getNomeRicetta());

		if(ricetta!=null) {
			return "redirect:ricetta/"+ricetta.getId();
		}

		return "redirect:/ricetta/-1"; //Un modo carino per sfruttare il template della ricetta per una ricetta che non esiste
	}

	@PostMapping("/ricercaPerIngredienteRicetta")
	public String showRicetteConStessoIngrediente(@Valid @ModelAttribute("ingredienteInfos") Ingrediente ingredienteInfos,
			BindingResult bindingResult, Model model) {

		if(bindingResult.hasFieldErrors("ingredienteInfos")) {
			return "redirect:/ingrediente/-1"; //Un modo carino per sfruttare il template dell'ingrediente per una dell'ingrediente che non esiste
		}

		ingredienteInfos = this.ingredienteService.findByNome(ingredienteInfos.getNome());

		if(ingredienteInfos==null) {
			return "redirect:/ingrediente/-1"; //Un modo carino per sfruttare il template dell'ingrediente per una dell'ingrediente che non esiste
		}

		List<Ricetta> allRicette = (List<Ricetta>) this.ricettaService.findAllByIngrediente(ingredienteInfos);

		model.addAttribute("allRicette", allRicette);
		return "elencoRicette.html";
	}




	/*===============================================================================================*/
	/*                                        SUPPORT METHODS                                        */
	/*===============================================================================================*/




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


	private void setupModificaIngredientiRicetta(Ricetta ricetta, Model model) {

		List<Ingrediente> allIngredientiMessi = new ArrayList<>(ricetta.getIngrediente2quantity().keySet()); //La lista degli ingredienti presenti nella ricetta

		List<Ingrediente> allIngredientiDisponibili = (List<Ingrediente>) this.ingredienteService.findAll();

		allIngredientiDisponibili.removeAll(allIngredientiMessi);

		model.addAttribute("allIngredientiMessi", allIngredientiMessi);
		model.addAttribute("allIngredientiDisponibili", allIngredientiDisponibili);
		model.addAttribute("ricetta", ricetta);
	}

}
