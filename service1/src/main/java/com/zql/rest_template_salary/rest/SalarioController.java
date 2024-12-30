package com.zql.rest_template_salary.rest;

import com.zql.rest_template_salary.service.SalarioService;
import com.zql.rest_template_salary.DTO.TrabalhadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salario")
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @PostMapping
    public double calculateSalario(@RequestBody TrabalhadorDTO trabalhadorDTO) {
        return salarioService.calculateSalario(trabalhadorDTO);
    }
}