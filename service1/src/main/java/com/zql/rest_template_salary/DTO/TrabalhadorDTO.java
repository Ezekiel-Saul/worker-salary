package com.zql.rest_template_salary.DTO;


public class TrabalhadorDTO {

    private int id;
    private String name;
    private double horasCount;
    private String funcao;

    public TrabalhadorDTO(){
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
        return "TrabalhadorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", horasCount=" + horasCount +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
