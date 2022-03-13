package com.gamifiq.godgamer.missao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.model.Missao;
import com.gamifiq.godgamer.repository.MissaoRepository;
import com.gamifiq.godgamer.services.MissaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MissaoServiceTest {

    @Mock
    private MissaoRepository missaoRepository;
    private MissaoService underTest;
    Fase fase;
    Missao missao;

    @BeforeEach
    void setUp() {
        underTest = new MissaoService(missaoRepository);

        fase = new Fase();
        fase.setDescricao("Fase para teste de missao repository");
        fase.setDataInicio(LocalDateTime.now());
        fase.setDatafim(LocalDateTime.now().plusDays(7));
        fase.setId(Long.valueOf(0));

        missao = new Missao();
        missao.setDescricao("Missao para tesde de missao repository");
        missao.setEstimativa(7);
        missao.setFaseId(fase.getId());
        missao.setStatus("doing");
    }

    @Test
    void itShouldFindAll() {
        // when
        underTest.getMissoes();
        // then
        verify(missaoRepository).findAll();
    }

    @Test
    void itShouldFind() {
        // when
        when(missaoRepository.findById(missao.getId())).thenReturn(Optional.of(missao));
        underTest.getMissoes(missao.getId());        
        // then
        verify(missaoRepository).findById(missao.getId());
    }

    @Test
    void itShouldReturnErrorIfDoesNotFind() {
        //given
        String message = null;
        // when
        try{
            underTest.getMissoes(missao.getId());
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(missaoRepository).findById(missao.getId());
    }

    @Test
    void itShouldFindByFase() {
        //givn
        List<Missao> missoes = new ArrayList<Missao>();
        missoes.add(missao);
        // when
        when(missaoRepository.findByFaseId(fase.getId())).thenReturn(missoes);
        underTest.getMissoesByFase(fase.getId());        
        // then
        verify(missaoRepository).findByFaseId(fase.getId());
    }

    @Test
    void itShouldSaveMissao() {
        // when
        underTest.saveMissao(missao);
        // then
        ArgumentCaptor<Missao> missaoArgumentCaptor = ArgumentCaptor.forClass(Missao.class);
        verify(missaoRepository).save(missaoArgumentCaptor.capture());
        assertSame(missao, missaoArgumentCaptor.getValue());
    }

    @Test
    void itShouldUpdate() {
        // when
        when(missaoRepository.findById(missao.getId())).thenReturn(Optional.of(missao));
        underTest.updateMissao(missao);        
        // then
        verify(missaoRepository).save(missao);
    }

    @Test
    void itShouldReturnErrorIfDoesNotFindToUpdate() {
        //given
        String message = null;
        // when
        try{
            underTest.updateMissao(missao);
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(missaoRepository).findById(missao.getId());
    }

    @Test
    void itShouldDelete() {
        // when
        when(missaoRepository.findById(missao.getId())).thenReturn(Optional.of(missao));
        underTest.deleteMissao(missao.getId());        
        // then
        verify(missaoRepository).delete(missao);
    }

    @Test
    void itShouldReturnErrorIfDoesNotFindToDelete() {
        //given
        String message = null;
        // when
        try{
            underTest.deleteMissao(missao.getId());
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(missaoRepository).findById(missao.getId());
    }
}
