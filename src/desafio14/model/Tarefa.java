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
    }

    public boolean isConcluida() {
        return horasConcluidas >= horasEstimadas;
    }
}

/*
 * 📍 Nova regra de validação:
 * 
 * a tarefa não pode ter horas negativas
 * 
 * horasConcluidas não pode ser maior que horasEstimadas
 * 
 * 
 * Se os dados forem inválidos, o model deve impedir o cadastro (ex.:
 * IllegalArgumentException).
 */