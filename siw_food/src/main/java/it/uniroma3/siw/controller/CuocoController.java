package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.service.CuocoService;

@Controller
public class CuocoController {
	
	/*##############################################################*/
	/*##########################SERVICES############################*/
	/*##############################################################*/
	
	@Autowired
	private CuocoService cuocoService;

	/*##############################################################*/
	/*###########################METHODS############################*/
	/*##############################################################*/
//======================================================================\\

	/*##############################################################*/
	/*############################/cuochi###########################*/
	/*##############################################################*/

	@GetMapping("/elencoCuochi")
	public String showElencoCuochi(Model model) {
		Iterable<Cuoco> allcuochi = this.cuocoService.findAll();
		model.addAttribute("allcuochi", allcuochi);
		return "elencoCuochi.html";
	}

	@GetMapping("/cuoco/{id}")
	public String showCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		return "manga.html";
	}
	
	
}
