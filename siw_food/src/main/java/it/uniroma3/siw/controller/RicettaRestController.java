package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Ricetta;

import it.uniroma3.siw.service.RicettaService;

@RestController
public class RicettaRestController {
	
	@Autowired
	private RicettaService ricettaService;
	
	@GetMapping("/rest/ricetta/{id}")
	public Ricetta getRicetta(@PathVariable("id") Long id) {
		return this.ricettaService.findById(id);
	}
	
	@GetMapping("/rest/elencoRicette")
	public List<Ricetta> getElencoManga() {
		return (List<Ricetta>)this.ricettaService.findAll();
	}
}
