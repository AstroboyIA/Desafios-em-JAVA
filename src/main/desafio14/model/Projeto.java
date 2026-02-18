package main.desafio14.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {

    private String nome;

    public String getNome() {
        return nome;
    }

    private int prazoHoras;

    public int getPrazoHoras() {
        return prazoHoras;
    }

    public Projeto(int prazoHoras, String nome) {
        this.prazoHoras = prazoHoras;
        this.nome = nome;
    }

    private List<Tarefa> tarefas = new ArrayList<>();

    public void cadastrarTarefa(Tarefa tarefa) {
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

        if (totalHorasEstimadas == 0)
            return 0;

        return (totalHorasConcluidas * 100) / totalHorasEstimadas;
    }

    public int calcularPrevisaoHorasFinais() {
        int totalHorasEstimadas = 0;

        for (Tarefa tarefa : tarefas) {
            totalHorasEstimadas += tarefa.getHorasEstimadas();
        }

        if (totalHorasEstimadas == 0)
            return 0;

        return totalHorasEstimadas;

    }

    public SituacaoPrazo calcularSituacaoPrazo() {
        int previsaoHorasFinais = calcularPrevisaoHorasFinais();

        if (previsaoHorasFinais < prazoHoras) {
            return SituacaoPrazo.ADIANTADO;
        } else if (previsaoHorasFinais == prazoHoras) {
            return SituacaoPrazo.NO_PRAZO;
        } else {
            return SituacaoPrazo.ATRASADO;
        }
    }

    public int calcularPercentualConclusaoReal() {
        int horasConcluidas = 0;
        int conclusaoReal;

        for (Tarefa tarefa : tarefas) {
            horasConcluidas += tarefa.getHorasConcluidas();
        }

        if (prazoHoras == 0)
            return 0;

        conclusaoReal = (horasConcluidas * 100) / prazoHoras;

        if (conclusaoReal > 100) {
            return 100;
        }
        return conclusaoReal;
    }
}