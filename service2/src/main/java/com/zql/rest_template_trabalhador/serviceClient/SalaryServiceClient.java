package com.zql.rest_template_trabalhador.serviceClient;

import com.zql.rest_template_trabalhador.dto.TrabalhadorDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SalaryServiceClient {

    private RestTemplate restTemplate;

    public SalaryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getSalaryTrabalhador(TrabalhadorDTO trabalhadorDTO) {
        String salarioServiceURL = "http://service1:8083/salario";

        HttpEntity<TrabalhadorDTO> request = new HttpEntity<>(trabalhadorDTO);
        ResponseEntity<Double> response = restTemplate.exchange(salarioServiceURL, HttpMethod.POST, request, Double.class);

        return response.getBody();
    }
}