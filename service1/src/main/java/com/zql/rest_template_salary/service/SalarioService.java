package com.zql.rest_template_salary.service;

import com.zql.rest_template_salary.DTO.TrabalhadorDTO;
import org.springframework.stereotype.Service;

@Service
public class SalarioService {

    public double calculateSalario(TrabalhadorDTO trabalhadorDTO) {
        // Example: Base hourly rate varies by role
        double hourlyRate = switch (trabalhadorDTO.getFuncao().toLowerCase()) {
            case "manager" -> 50.0;
            case "developer" -> 30.0;
            case "intern" -> 15.0;
            default -> 20.0;
        };

        return hourlyRate * trabalhadorDTO.getHorasCount();
    }
}






