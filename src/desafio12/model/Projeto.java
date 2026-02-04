package desafio12.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String nome;

    public String getNome() {
        return nome;
    }

    public Projeto(String nome) {
        this.nome = nome;
    }

    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public int calcularTotalHorasEstimadas() {
        int totalHoras = 0;
        for (Tarefa tarefa : tarefas) {
            totalHoras += tarefa.getHorasEstimadas();
        }
        return totalHoras;
    }

    public enum TamanhoProjeto {
        PEQUENO,
        MEDIO,
        GRANDE
    }

    public TamanhoProjeto calcularTamanho() {

        int horas = calcularTotalHorasEstimadas();

        if (horas <= 20) {
            return TamanhoProjeto.PEQUENO;
        } else if (horas <= 50) {
            return TamanhoProjeto.MEDIO;
        } else {
            return TamanhoProjeto.GRANDE;
        }
    }
}