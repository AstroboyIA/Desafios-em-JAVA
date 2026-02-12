package desafio13.backend.model;

public class Tarefa {
    
    private String descricao;

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    private int horasEstimadas;
    private int horasConcluidas;

    public boolean isConcluida() {
        return horasConcluidas >= horasEstimadas;
    }
}
