package com.gamifiq.godgamer.services;

import java.util.List;

import com.gamifiq.godgamer.model.Missao;
import com.gamifiq.godgamer.repository.MissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MissaoService {
    @Autowired
    MissaoRepository repository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.repository = missaoRepository;
    }

    public List<Missao> getMissoes() {
        return repository.findAll();
    }

    public Missao getMissoes(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }

    public List<Missao> getMissoesByFase(Long id) {
        return repository.findByFaseId(id);
    }

    public Missao saveMissao(Missao missao) {
        return repository.save(missao);
    }

    public Missao updateMissao(Missao newMissao) {
        repository.findById(newMissao.getId()).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        Missao updatedMissao = repository.save(newMissao);
        return updatedMissao;
    }

    public ResponseEntity<?> deleteMissao(Long id) {
        Missao missao = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        repository.delete(missao);

        return ResponseEntity.ok().build();
    }
}
