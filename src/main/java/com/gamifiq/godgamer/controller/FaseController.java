package com.gamifiq.godgamer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.services.FaseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/v1")
public class FaseController {

	@Autowired
	FaseService faseService;

	@GetMapping("/fases")
	public List<Fase> getFases() {
		return faseService.getFases();
	}

	@GetMapping("/fase/{id}")
	public Fase getFases(@PathVariable(value = "id") Long id) {
		return faseService.getFases(id);
	}

	@PostMapping("/fase")
	public Fase saveFases(@RequestBody Fase fase) {
		return faseService.saveFases(fase);
	}

	@PutMapping("/fase")
	public Fase updateFase(@RequestBody Fase newFase) {
		return faseService.updateFase(newFase);
	}

	@DeleteMapping("/fase/{id}")
	public ResponseEntity<?> deleteFase(@PathVariable(value = "id") Long id) {
		return faseService.deleteFase(id);
	}

}
