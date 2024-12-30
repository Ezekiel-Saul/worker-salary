package com.zql.rest_template_trabalhador.service;

import com.zql.rest_template_trabalhador.Entity.Trabalhador;
import com.zql.rest_template_trabalhador.dto.TrabalhadorDTO;
import com.zql.rest_template_trabalhador.serviceClient.SalaryServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrabalhadorService {

    private List<Trabalhador> trabalhadors;
    private SalaryServiceClient salaryServiceClient;

    public TrabalhadorService(SalaryServiceClient salaryServiceClient) {
        this.trabalhadors = new ArrayList<>();
        this.salaryServiceClient = salaryServiceClient;
    }

    public Trabalhador addTrabalhador(Trabalhador trabalhador) {
        trabalhadors.add(trabalhador);
        return trabalhador;
    }

    public Trabalhador getTrabalhador(int id) {
        return trabalhadors.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid Trabalhador ID"));
    }

    public List<Trabalhador> getAllTrabalhadores() {
        return trabalhadors;
    }

    public double getSalaryByIDTrabalhador(int id) {
        Trabalhador trabalhador = getTrabalhador(id);
        TrabalhadorDTO tdto = new TrabalhadorDTO();
        tdto.setHorasCount(trabalhador.getHorasCount());
        tdto.setFuncao(trabalhador.getFuncao());
        tdto.setName(trabalhador.getName());
        tdto.setId(trabalhador.getId());
        return salaryServiceClient.getSalaryTrabalhador(tdto);
    }

    public boolean delTrabalhador(int id){
        return trabalhadors.removeIf(s->s.getId()==id);

    }
}
