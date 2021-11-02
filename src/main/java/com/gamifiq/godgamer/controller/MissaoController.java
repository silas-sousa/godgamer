package com.gamifiq.godgamer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiq.godgamer.model.Missao;
import com.gamifiq.godgamer.repository.MissaoRepository;

@RestController
@RequestMapping(path = "/api/v1")
public class MissaoController {
	
	@Autowired
    MissaoRepository repository;
	
	@GetMapping("/missoes")
	public List<Missao> getMissaos(){
		return repository.findAll();
	}
	
	@GetMapping("/missao/{id}")
	public Missao getMissaos(@PathVariable(value = "id") Long id){
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
	}
	
	@GetMapping("/missoesByFase/{id}")
	public List<Missao> getMissoesByFase(@PathVariable(value = "id") Long id){
		return repository.findByFaseId(id);
	}
	
	@PostMapping("/missao")
	public Missao getMissaos(@RequestBody Missao missao){
		return repository.save(missao);
	}
	
	@PutMapping("/missao")
    public Missao updateMissao(@RequestBody Missao newMissao) {
		
        repository.findById(newMissao.getId()).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        Missao updatedMissao = repository.save(newMissao);
        return updatedMissao;
    }

    @DeleteMapping("/missao/{id}")
    public ResponseEntity<?> deleteMissao(@PathVariable(value = "id") Long id) {
    	Missao missao = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

    	repository.delete(missao);

        return ResponseEntity.ok().build();
    }
	
	
}
