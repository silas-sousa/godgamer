package com.gamifiq.godgamer.fase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gamifiq.godgamer.model.Fase;
import com.gamifiq.godgamer.repository.FaseRepository;
import com.gamifiq.godgamer.services.FaseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FaseServiceTest {

    @Mock
    private FaseRepository faseRepository;
    private FaseService underTest;
    Fase fase;

    @BeforeEach
    void setUp() {
        underTest = new FaseService(faseRepository);
        fase = new Fase();
        fase.setDescricao("Fase para teste de missao repository");
        fase.setDataInicio(LocalDateTime.now());
        fase.setDatafim(LocalDateTime.now().plusDays(7));
        fase.setId(Long.valueOf(0));
    }

    @Test
    void itShouldFindAll() {
        // when
        underTest.getFases();
        // then
        verify(faseRepository).findAll();
    }

    @Test
    void itShouldFind() {
        // when
        when(faseRepository.findById(fase.getId())).thenReturn(Optional.of(fase));
        underTest.getFases(fase.getId());        
        // then
        verify(faseRepository).findById(fase.getId());
    }

    @Test
    void itShouldReturnErrorIfDoesNotFind() {
        //given
        String message = null;
        // when
        try{
            underTest.getFases(fase.getId());
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(faseRepository).findById(fase.getId());
    }

    @Test
    void itShouldSaveFase() {
        //given
        
        // when
        underTest.saveFases(fase);
        // then
        ArgumentCaptor<Fase> faseArgumentCaptor = ArgumentCaptor.forClass(Fase.class);
        verify(faseRepository).save(faseArgumentCaptor.capture());
        assertSame(fase, faseArgumentCaptor.getValue());
    }

    @Test
    void itShouldUpdate() {
        // when
        when(faseRepository.findById(fase.getId())).thenReturn(Optional.of(fase));
        underTest.updateFase(fase);        
        // then
        verify(faseRepository).save(fase);
    }

    @Test
    void itShouldReturnErrorIfDoesNotFindToUpdate() {
        //given
        String message = null;
        // when
        try{
            underTest.updateFase(fase);
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(faseRepository).findById(fase.getId());
    }

    @Test
    void itShouldDelete() {
        // when
        when(faseRepository.findById(fase.getId())).thenReturn(Optional.of(fase));
        underTest.deleteFase(fase.getId());        
        // then
        verify(faseRepository).delete(fase);
    }

    @Test
    void itShouldReturnErrorIfDoesNotFindToDelete() {
        //given
        String message = null;
        // when
        try{
            underTest.deleteFase(fase.getId());
        } catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
        verify(faseRepository).findById(fase.getId());
    }
}
