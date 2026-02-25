package desafio14.model;

public class Tarefa {
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private int horasEstimadas;

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    private int horasConcluidas;

    public int getHorasConcluidas() {
        return horasConcluidas;
    }

    public Tarefa(String descricao, int horasEstimadas, int horasConcluidas) {
        this.descricao = descricao;
        this.horasEstimadas = horasEstimadas;
        this.horasConcluidas = horasConcluidas;

        if (horasEstimadas < 0) {
            System.out.println("A estimativa não pode ser negativa");
            throw new IllegalArgumentException();
        }

        if (horasConcluidas < 0) {
            System.out.println("A conclusão não pode ser negativa");
            throw new IllegalArgumentException();
        }

        if (horasConcluidas > horasEstimadas) {
            System.out.println("A conclusão não pode ser maior que a estimativa");
            throw new IllegalArgumentException();
        }
    }

    public boolean isConcluida() {
        return horasConcluidas >= horasEstimadas;
    }
}