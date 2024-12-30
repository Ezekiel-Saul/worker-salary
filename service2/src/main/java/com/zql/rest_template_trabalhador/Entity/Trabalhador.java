package com.zql.rest_template_trabalhador.Entity;


public class Trabalhador {

    private int id;
    private String name;
    private double horasCount;
    private String funcao;

    public Trabalhador(String name, int id, String funcao) {
        this.horasCount = 0;
        this.name = name;
        this.id = id;
        this.funcao = funcao;
    }
    public Trabalhador(){
        this.horasCount = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHorasCount() {
        return horasCount;
    }

    public void setHorasCount(double horasCount) {
        this.horasCount = horasCount;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Trabalhador{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", horasCount=" + horasCount +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
