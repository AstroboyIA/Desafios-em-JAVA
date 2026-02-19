package main.desafio13.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String nome;

    public Projeto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public StatusProjeto calcularStatus() {

        if (tarefas.isEmpty()) {
            return StatusProjeto.NAO_INICIADO;
        }

        for (Tarefa tarefa : tarefas) {

            if (!tarefa.isConcluida()) {
                return StatusProjeto.EM_ANDAMENTO;
            }
        }

        return StatusProjeto.CONCLUIDO;

    }

    public int percentualProjeto() {

        int totalHorasEstimadas = 0;
        int totalHorasConcluidas = 0;

        for (Tarefa tarefa : tarefas) {
            totalHorasEstimadas += tarefa.getHorasEstimadas();
            totalHorasConcluidas += tarefa.getHorasConcluidas();
        }

        if (totalHorasEstimadas == 0) return 0;
            
        return (totalHorasConcluidas * 100) / totalHorasEstimadas;
    }

    public TamanhoProjeto calcularTamanho() {

        int tamanho = 0;

        for (Tarefa tarefa : tarefas) {
            tamanho += tarefa.getHorasEstimadas();
        }

        if (tamanho <= 20) {
            return TamanhoProjeto.PEQUENO;
        } else if (tamanho <= 50) {
            return TamanhoProjeto.MEDIO;
        } else {
            return TamanhoProjeto.GRANDE;
        }
    }
}