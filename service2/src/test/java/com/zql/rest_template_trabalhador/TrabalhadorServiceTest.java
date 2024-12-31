package com.zql.rest_template_trabalhador;

import com.zql.rest_template_trabalhador.Entity.Trabalhador;
import com.zql.rest_template_trabalhador.dto.TrabalhadorDTO;
import com.zql.rest_template_trabalhador.service.TrabalhadorService;
import com.zql.rest_template_trabalhador.serviceClient.SalaryServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrabalhadorServiceTest {

    private TrabalhadorService service;
    private SalaryServiceClient mockClient;

    @BeforeEach
    void setUp() {
        mockClient = mock(SalaryServiceClient.class);
        service = new TrabalhadorService(mockClient); // Assuming TrabalhadorService has a constructor that takes a SalaryServiceClient
    }

    @Test
    void testGetSalaryByIDTrabalhador() {
        // Step 1: Add a Trabalhador to the service
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setId(1);
        trabalhador.setFuncao("developer");
        trabalhador.setName("Emily");
        trabalhador.setHorasCount(40);
        service.addTrabalhador(trabalhador); // Assuming addTrabalhador method exists to add to a list

        // Step 2: Mock the SalaryServiceClient response
        when(mockClient.getSalaryTrabalhador(any(TrabalhadorDTO.class))).thenReturn(1200.0);

        // Step 3: Call the method under test
        double salary = service.getSalaryByIDTrabalhador(1);

        // Step 4: Verify the result
        assertEquals(1200.0, salary, 0.001);
    }

    // More tests for other methods
}