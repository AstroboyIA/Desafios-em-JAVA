package desafio13.backend.model;

public class Tarefa {

    private String descricao;
    private int horasEstimadas;
    private int horasConcluidas;

    public int getHorasConcluidas() {
        return horasConcluidas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void adicionarTarefa(String descricao, int horasEstimadas, int horasConcluidas) {
        this.descricao = descricao;
        this.horasEstimadas = horasEstimadas;
        this.horasConcluidas = horasConcluidas;
    }

    public boolean isConcluida() {
        return horasConcluidas >= horasEstimadas;
    }
}
