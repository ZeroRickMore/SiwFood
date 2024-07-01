package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Ingrediente;

import it.uniroma3.siw.service.IngredienteService;

@RestController
public class IngredienteRestController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/rest/ingrediente/{id}")
	public Ingrediente getIngrediente(@PathVariable("id") Long id) {
		return this.ingredienteService.findById(id);
	}
	
	@GetMapping("/rest/elencoIngredienti")
	public List<Ingrediente> getElencoManga() {
		return (List<Ingrediente>)this.ingredienteService.findAll();
	}
	
}
