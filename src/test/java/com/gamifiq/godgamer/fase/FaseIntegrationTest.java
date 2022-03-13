package com.gamifiq.godgamer.fase;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamifiq.godgamer.model.Fase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FaseIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Fase fase;

    @BeforeEach
    void setUp() {
        fase = new Fase();
        fase.setDescricao("Fase para teste de missao repository");
        fase.setDataInicio(LocalDateTime.now());
        fase.setDatafim(LocalDateTime.now().plusDays(7));
        fase.setId(Long.valueOf(0));
    }

    @Test
    void itShouldListAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fases"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void itShouldAdd() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fase")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fase)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String resultString = mvcResult.getResponse().getContentAsString();
        Long id = Long.valueOf(objectMapper.readTree(resultString).get("id").asText());
        this.fase.setId(id);
    }

    @Test
    void itShouldNotGetById() throws Exception {
        // given
        String message = null;
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fase/9000"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"));
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getCause().getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
    }

    @Test
    void itShouldGetById() throws Exception {
        itShouldAdd();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fase/"+this.fase.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    void itShouldNotUpdate() throws Exception {
        // given
        String message = null;
        fase.setId(Long.valueOf(9000));
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/fase")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(fase)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getCause().getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
    }

    @Test
    void itShouldUpdate() throws Exception {
        // given
        itShouldAdd();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/fase")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fase)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void itShouldNotDeleteById() throws Exception {
        // given
        String message = null;
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fase/9000"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getCause().getMessage();
        }
        // then
        assertEquals("Registro não encontrado", message);
    }

    @Test
    void itShouldDeleteById() throws Exception {
        itShouldAdd();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fase/"+fase.getId().toString()))
                .andExpect(status().isOk());

    }
}
