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

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.repository.FaseRepository;

@RestController
@RequestMapping(path = "/api/v1")
public class FaseController {
	
	@Autowired
    FaseRepository repository;
	
	@GetMapping("/fases")
	public List<Fase> getFases(){
		return repository.findAll();
	}
	
	@GetMapping("/fase/{id}")
	public Fase getFases(@PathVariable(value = "id") Long id){
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
	}
	
	@PostMapping("/fase")
	public Fase getFases(@RequestBody Fase fase){
		return repository.save(fase);
	}
	
	@PutMapping("/fase")
    public Fase updateFase(@RequestBody Fase newFase) {

        repository.findById(newFase.getId()).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        Fase updatedFase = repository.save(newFase);
        return updatedFase;
    }

    @DeleteMapping("/fase/{id}")
    public ResponseEntity<?> deleteFase(@PathVariable(value = "id") Long id) {
    	Fase fase = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

    	repository.delete(fase);

        return ResponseEntity.ok().build();
    }
	
	
}
