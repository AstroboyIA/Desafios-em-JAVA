package main.desafio11.model;

public class Treino {
    String descricao;
    int duracaoMinutos;
    
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public Treino(String descricao, int duracaoMinutos) {
        this.descricao = descricao;
        this.duracaoMinutos = duracaoMinutos;
    }

}
