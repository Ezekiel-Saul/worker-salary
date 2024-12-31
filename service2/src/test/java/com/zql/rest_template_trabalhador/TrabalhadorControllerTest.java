package com.zql.rest_template_trabalhador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zql.rest_template_trabalhador.Entity.Trabalhador;
import com.zql.rest_template_trabalhador.rest.TrabalhadorController;
import com.zql.rest_template_trabalhador.service.TrabalhadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class TrabalhadorControllerTest {

    @Autowired
    private TrabalhadorController trabalhadorController;

    @MockBean
    private TrabalhadorService trabalhadorService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        // Manually set up MockMvc with the controller and mocked service
        mockMvc = standaloneSetup(trabalhadorController).build();
    }

    @Test
    public void testCreateAndDeleteTrabalhador() throws Exception {
        // Create a Trabalhador
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setFuncao("developer");
        trabalhador.setName("Wimy");
        trabalhador.setHorasCount(40);
        trabalhador.setId(3);

        // Mock service to return true when adding a trabalhador
        when(trabalhadorService.addTrabalhador(any(Trabalhador.class))).thenReturn(trabalhador);

        // POST to create Trabalhador
        mockMvc.perform(post("/trabalhadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trabalhador)))
                .andExpect(status().isOk());

        // Mock service to return true when deleting a trabalhador
        when(trabalhadorService.delTrabalhador(3)).thenReturn(true);

        // DELETE to remove Trabalhador
        mockMvc.perform(delete("/trabalhadores/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("true")); // Expecting a boolean response

        // Verify that delTrabalhador was called with ID 3
        verify(trabalhadorService, times(1)).delTrabalhador(3);
    }
}