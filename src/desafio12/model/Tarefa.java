package main.desafio12.model;

public class Tarefa {
    private String descricao;
    private int horasEstimadas;

    public String getDescricao() {
        return descricao;
    }
    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public Tarefa(String descricao, int horasEstimadas) {
        this.descricao = descricao;
        this.horasEstimadas = horasEstimadas;
    }
}