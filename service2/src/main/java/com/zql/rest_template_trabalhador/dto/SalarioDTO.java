package com.zql.rest_template_trabalhador.dto;

public class SalarioDTO {

    private int id;
    private int idTrabalhador;
    private double amount;

    public SalarioDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(int idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salario{" +
                "id=" + id +
                ", idTrabalhador=" + idTrabalhador +
                ", amount=" + amount +
                '}';
    }
}
