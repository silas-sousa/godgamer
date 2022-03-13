package com.gamifiq.godgamer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiq.godgamer.model.Fase;

@Repository
public interface FaseRepository extends JpaRepository<Fase,Long>{

}
