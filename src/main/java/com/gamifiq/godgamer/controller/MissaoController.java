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

import com.gamifiq.godgamer.model.Missao;
import com.gamifiq.godgamer.services.MissaoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/v1")
public class MissaoController {

	@Autowired
	MissaoService missaoService;

	@GetMapping("/missoes")
	public List<Missao> getMissoes() {
		return missaoService.getMissoes();
	}

	@GetMapping("/missao/{id}")
	public Missao getMissoes(@PathVariable(value = "id") Long id) {
		return missaoService.getMissoes(id);
	}

	@GetMapping("/missoesByFase/{id}")
	public List<Missao> getMissoesByFase(@PathVariable(value = "id") Long id) {
		return missaoService.getMissoesByFase(id);
	}

	@PostMapping("/missao")
	public Missao saveMissao(@RequestBody Missao missao) {
		return missaoService.saveMissao(missao);
	}

	@PutMapping("/missao")
	public Missao updateMissao(@RequestBody Missao newMissao) {
		return missaoService.updateMissao(newMissao);
	}

	@DeleteMapping("/missao/{id}")
	public ResponseEntity<?> deleteMissao(@PathVariable(value = "id") Long id) {
		return missaoService.deleteMissao(id);
	}

}
