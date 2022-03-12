package com.gamifiq.godgamer.missao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.model.Missao;
import com.gamifiq.godgamer.repository.FaseRepository;
import com.gamifiq.godgamer.repository.MissaoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class MissaoRepositoryTest {
    @Autowired
    MissaoRepository missaoRepository;
    @Autowired
    FaseRepository faseRepository;

    @Test
    void itShouldNotfindBuFaseId(){
        //given
        Long id = Long.valueOf(0);
        //when
        List<Missao> result = missaoRepository.findByFaseId(id); 
        //then
        assertEquals(new ArrayList<Missao>(), result);
    }

    @Test
    void itShouldfindByFaseId(){
        //given
        Fase fase = new Fase();
        fase.setDescricao("Fase para teste de missao repository");
        fase.setDataInicio(LocalDateTime.now());
        fase.setDatafim(LocalDateTime.now().plusDays(7));
        //fase.setId(Long.valueOf(0));
        fase = faseRepository.save(fase);
        
        Missao missao = new Missao();
        missao.setDescricao("Missao para tesde de missao repository");
        missao.setEstimativa(7);
        missao.setFaseId(fase.getId());
        missao.setStatus("doing");
        missaoRepository.save(missao);

        //when
        List<Missao> result = missaoRepository.findByFaseId(fase.getId());

        //then
        assertNotNull(result);
    }
}
