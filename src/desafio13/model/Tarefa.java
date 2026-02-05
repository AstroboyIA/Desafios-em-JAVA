package desafio13.model;

public class Tarefa {
    
    String descricao;

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    int horasEstimadas;
    int horasConcluidas;

    public boolean isConcluida() {
        return horasConcluidas >= horasEstimadas;
    }
}
