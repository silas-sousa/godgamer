package com.gamifiq.godgamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamifiq.godgamer.model.Missao;

public interface MissaoRepository extends JpaRepository<Missao,Long>{
	
	List<Missao> findByFaseId(Long faseId);

}
