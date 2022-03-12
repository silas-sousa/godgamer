package com.gamifiq.godgamer.services;

import java.util.List;

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.repository.FaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FaseService {
    @Autowired
    FaseRepository repository;

    public FaseService(FaseRepository faseRepository) {
        this.repository = faseRepository;
    }

    public List<Fase> getFases() {
        return repository.findAll();
    }

    public Fase getFases(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }

    public Fase saveFases(Fase fase) {
        return repository.save(fase);
    }

    public Fase updateFase(Fase newFase) {
        repository.findById(newFase.getId()).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        Fase updatedFase = repository.save(newFase);
        return updatedFase;
    }

    public ResponseEntity<?> deleteFase(Long id) {
        Fase fase = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        repository.delete(fase);

        return ResponseEntity.ok().build();
    }

}
